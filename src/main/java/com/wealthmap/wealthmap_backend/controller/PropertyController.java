package com.wealthmap.wealthmap_backend.controller;
import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import com.wealthmap.wealthmap_backend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // open for React frontend
public class PropertyController {

    private final PropertyService propertyService;

    // 1. Get all properties
    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    // 2. Get property by ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    // 3. Create new property
    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO dto) {
        return ResponseEntity.ok(propertyService.createProperty(dto));
    }

    // 4. Update property
    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO dto) {
        return ResponseEntity.ok(propertyService.updateProperty(id, dto));
    }

    //  5. Delete property
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    //  6. Filter by value >= min
    @GetMapping("/filter/value")
    public ResponseEntity<List<PropertyDTO>> filterByMinValue(@RequestParam double minValue) {
        return ResponseEntity.ok(propertyService.filterByMinValue(minValue));
    }

    // 7. Filter by type
    @GetMapping("/filter/type")
    public ResponseEntity<List<PropertyDTO>> filterByType(@RequestParam String type) {
        return ResponseEntity.ok(propertyService.filterByPropertyType(type));
    }

    //  8. Filter by size range
    @GetMapping("/filter/size")
    public ResponseEntity<List<PropertyDTO>> filterBySizeRange(@RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(propertyService.filterBySizeRange(min, max));
    }

    // 9. Search by owner name (partial match)
    @GetMapping("/search/owner")
    public ResponseEntity<List<PropertyDTO>> searchByOwner(@RequestParam String name) {
        return ResponseEntity.ok(propertyService.searchByOwnerName(name));
    }

    //  10. Filter by bounding box (for map view)
    @GetMapping("/filter/map-bounds")
    public ResponseEntity<List<PropertyDTO>> filterByMapBounds(
            @RequestParam double minLat,
            @RequestParam double maxLat,
            @RequestParam double minLng,
            @RequestParam double maxLng) {
        return ResponseEntity.ok(propertyService.filterByMapBounds(minLat, maxLat, minLng, maxLng));
    }



}
