package com.wealthmap.wealthmap_backend.service;
import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import com.wealthmap.wealthmap_backend.model.Property;
import com.wealthmap.wealthmap_backend.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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

}
