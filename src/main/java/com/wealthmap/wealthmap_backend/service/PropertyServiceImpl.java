package com.wealthmap.wealthmap_backend.service;
import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyResponse;
import com.wealthmap.wealthmap_backend.model.Property;
import com.wealthmap.wealthmap_backend.repository.PropertyRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    private PropertyDTO convertToDTO(Property property) {
        return modelMapper.map(property, PropertyDTO.class);
    }

    private Property convertToEntity(PropertyDTO dto) {
        return modelMapper.map(dto, Property.class);
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
        existing.setLatitude(dto.getLatitude());
        existing.setLongitude(dto.getLongitude());
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
    public List<PropertyDTO> filterByMinValue(double minValue) {
        return propertyRepository.findAll().stream()
                .filter(p -> p.getValue() >= minValue)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> filterByPropertyType(String type) {
        return propertyRepository.findAll().stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase(type))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> filterBySizeRange(double min, double max) {
        return propertyRepository.findAll().stream()
                .filter(p -> p.getSizeInSqFt() >= min && p.getSizeInSqFt() <= max)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> searchByOwnerName(String name) {
        return propertyRepository.findAll().stream()
                .filter(p -> p.getOwnerName() != null && p.getOwnerName().toLowerCase().contains(name.toLowerCase()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> filterByMapBounds(double minLat, double maxLat, double minLng, double maxLng) {
        return propertyRepository.findAll().stream()
                .filter(p ->
                        p.getLatitude() >= minLat &&
                                p.getLatitude() <= maxLat &&
                                p.getLongitude() >= minLng &&
                                p.getLongitude() <= maxLng)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
