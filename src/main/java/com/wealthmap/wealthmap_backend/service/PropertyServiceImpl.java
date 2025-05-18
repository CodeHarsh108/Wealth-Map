package com.wealthmap.wealthmap_backend.service;
import com.wealthmap.wealthmap_backend.dto.ClusterDTO;
import com.wealthmap.wealthmap_backend.dto.PolygonSearchDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyResponse;
import com.wealthmap.wealthmap_backend.model.Property;
import com.wealthmap.wealthmap_backend.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Coordinate;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    private final GeometryFactory geometryFactory = new GeometryFactory();


    private PropertyDTO convertToDTO(Property property) {
        PropertyDTO dto = modelMapper.map(property, PropertyDTO.class);

        // Extract latitude/longitude from Point (assuming location is mandatory)
        Point location = property.getLocation();
        dto.setLatitude(location.getY());  // latitude = Y coordinate
        dto.setLongitude(location.getX()); // longitude = X coordinate

        return dto;
    }


    private Property convertToEntity(PropertyDTO dto) {
        Property property = modelMapper.map(dto, Property.class);

        // Create and set Point (validation already handled by @NotNull)
        Point point = geometryFactory.createPoint(
                new Coordinate(dto.getLongitude(), dto.getLatitude())
        );
        point.setSRID(4326);
        property.setLocation(point);

        return property;
    }

    @Override
    public PropertyResponse getAllProperties(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Property> propertyPage = propertyRepository.findAll(pageDetails);
        List<Property> properties = propertyPage.getContent();
        if(properties.isEmpty())
            throw new RuntimeException("No properties found");
        List<PropertyDTO> propertyDTOS = properties.stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class)).toList();
        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setContent(propertyDTOS);
        propertyResponse.setPageNumber(propertyPage.getNumber());
        propertyResponse.setPageSize(propertyPage.getSize());
        propertyResponse.setTotalElements(propertyPage.getTotalElements());
        propertyResponse.setTotalPages(propertyPage.getTotalPages());
        propertyResponse.setLastPage(propertyPage.isLast());
        return propertyResponse;
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + id));
        return convertToDTO(property);
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO dto) {
        Property saved = propertyRepository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    @Override
    public PropertyDTO updateProperty(Long id, PropertyDTO dto) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + id));

        // Update fields from DTO to existing entity
        existing.setAddress(dto.getAddress());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setZipCode(dto.getZipCode());
        if (dto.getLatitude() != null && dto.getLongitude() != null) {
            Point point = geometryFactory.createPoint(new Coordinate(dto.getLongitude(), dto.getLatitude()));
            point.setSRID(4326);
            existing.setLocation(point);
        }
        existing.setValue(dto.getValue());
        existing.setSizeInSqFt(dto.getSizeInSqFt());
        existing.setPropertyType(dto.getPropertyType());
        existing.setOwnerName(dto.getOwnerName());
        existing.setCompanyName(dto.getCompanyName());
        existing.setEstimatedNetWorth(dto.getEstimatedNetWorth());

        // Save and return updated DTO
        Property updated = propertyRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public PropertyResponse filterByMinValue(double minValue, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Property> propertyPage = propertyRepository.findByValueGreaterThanEqual(minValue, pageable);
        List<PropertyDTO> dtos = propertyPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        PropertyResponse response = new PropertyResponse();
        response.setContent(dtos);
        response.setPageNumber(propertyPage.getNumber());
        response.setPageSize(propertyPage.getSize());
        response.setTotalElements(propertyPage.getTotalElements());
        response.setTotalPages(propertyPage.getTotalPages());
        response.setLastPage(propertyPage.isLast());
        return response;
    }


    @Override
    public PropertyResponse filterByPropertyType(String type, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Property> propertyPage = propertyRepository.findByPropertyTypeIgnoreCase(type, pageable);
        List<PropertyDTO> dtos = propertyPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        PropertyResponse response = new PropertyResponse();
        response.setContent(dtos);
        response.setPageNumber(propertyPage.getNumber());
        response.setPageSize(propertyPage.getSize());
        response.setTotalElements(propertyPage.getTotalElements());
        response.setTotalPages(propertyPage.getTotalPages());
        response.setLastPage(propertyPage.isLast());
        return response;
    }


    @Override
    public PropertyResponse filterBySizeRange(double min, double max, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Property> page = propertyRepository.findBySizeInSqFtBetween(min, max, pageable);

        List<PropertyDTO> dtos = page.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        PropertyResponse response = new PropertyResponse();
        response.setContent(dtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }


    @Override
    public PropertyResponse searchByOwnerName(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Property> page = propertyRepository.findByOwnerNameContainingIgnoreCase(name, pageable);

        List<PropertyDTO> dtos = page.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        PropertyResponse response = new PropertyResponse();
        response.setContent(dtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }


    @Override
    public PropertyResponse filterByMapBounds(double minLat, double maxLat, double minLng, double maxLng,
                                              Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Property> page = propertyRepository.findByLocationWithinBounds(minLat, maxLat, minLng, maxLng, pageable);

        List<PropertyDTO> dtos = page.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        PropertyResponse response = new PropertyResponse();
        response.setContent(dtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }


    @Override
    public PropertyResponse filterByDistance(double lat, double lng, double radiusInKm,
                                             int pageNumber, int pageSize,
                                             String sortBy, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        double radiusInMeters = radiusInKm * 1000;

        Page<Property> propertyPage = propertyRepository.findPropertiesWithinDistance(lat, lng, radiusInMeters, pageable);
        List<PropertyDTO> dtos = propertyPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        PropertyResponse response = new PropertyResponse();
        response.setContent(dtos);
        response.setPageNumber(propertyPage.getNumber());
        response.setPageSize(propertyPage.getSize());
        response.setTotalElements(propertyPage.getTotalElements());
        response.setTotalPages(propertyPage.getTotalPages());
        response.setLastPage(propertyPage.isLast());

        return response;
    }


    @Override
    public List<ClusterDTO> getClusters(double lat, double lng, double radius, double gridSize) {
        List<Object[]> results = propertyRepository.clusterProperties(lat, lng, radius, gridSize);
        return results.stream().map(row -> {
            double longitude = ((Number) row[0]).doubleValue();
            double latitude = ((Number) row[1]).doubleValue();
            long count = ((Number) row[2]).longValue();
            double avgValue = row[3] != null ? ((Number) row[3]).doubleValue() : 0.0;
            double avgNetWorth = row[4] != null ? ((Number) row[4]).doubleValue() : 0.0;
            return new ClusterDTO(latitude, longitude, count, avgValue, avgNetWorth);
        }).toList();
    }

    @Override
    public List<PropertyDTO> findPropertiesWithinPolygon(PolygonSearchDTO polygonSearchDTO) {
        StringBuilder wkt = new StringBuilder("POLYGON((");
        for (Double[] coord : polygonSearchDTO.getCoordinates()) {
            wkt.append(coord[0]).append(" ").append(coord[1]).append(", ");
        }
        // Close polygon: repeat first coord at end
        Double[] first = polygonSearchDTO.getCoordinates().get(0);
        wkt.append(first[0]).append(" ").append(first[1]).append("))");

        List<Property> properties = propertyRepository.findWithinPolygon(wkt.toString());
        return properties.stream().map(this::convertToDTO).toList();
    }





}
