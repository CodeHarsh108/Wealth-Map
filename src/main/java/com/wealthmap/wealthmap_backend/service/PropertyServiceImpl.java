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

}
