package com.wealthmap.wealthmap_backend.controller;
import com.wealthmap.wealthmap_backend.config.AppConstants;
import com.wealthmap.wealthmap_backend.dto.ClusterDTO;
import com.wealthmap.wealthmap_backend.dto.PolygonSearchDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyResponse;
import com.wealthmap.wealthmap_backend.model.Property;
import com.wealthmap.wealthmap_backend.repository.PropertyRepository;
import com.wealthmap.wealthmap_backend.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    private final PropertyRepository propertyRepository;

    // 1. Get all properties
    @GetMapping
    public ResponseEntity<PropertyResponse> getAllProperties(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue =AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue =AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue =AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        PropertyResponse propertyResponse = propertyService.getAllProperties(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(propertyResponse, HttpStatus.OK);
    }



    // 2. Get property by ID
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    // 3. Create new property
    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@Valid @RequestBody PropertyDTO dto) {
        PropertyDTO saved = propertyService.createProperty(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    // 4. Update property
    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody @Valid PropertyDTO dto) {
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
    public ResponseEntity<PropertyResponse> filterByMinValue(
            @RequestParam double minValue,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        PropertyResponse response = (PropertyResponse) propertyService.filterByMinValue(minValue, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 7. Filter by type
    @GetMapping("/filter/type")
    public ResponseEntity<PropertyResponse> filterByType(
            @RequestParam String type,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        PropertyResponse response = (PropertyResponse) propertyService.filterByPropertyType(type, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //  8. Filter by size range
    @GetMapping("/filter/size")
    public ResponseEntity<PropertyResponse> filterBySizeRange(
            @RequestParam double min,
            @RequestParam double max,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        PropertyResponse response = (PropertyResponse) propertyService.filterBySizeRange(min, max, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // 9. Search by owner name (partial match)
    @GetMapping("/search/owner")
    public ResponseEntity<PropertyResponse> searchByOwner(
            @RequestParam String name,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        PropertyResponse response = (PropertyResponse) propertyService.searchByOwnerName(name, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //  10. Filter by bounding box (for map view)
    @GetMapping("/filter/map-bounds")
    public ResponseEntity<PropertyResponse> filterByMapBounds(
            @RequestParam double minLat,
            @RequestParam double maxLat,
            @RequestParam double minLng,
            @RequestParam double maxLng,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        PropertyResponse response = propertyService.filterByMapBounds(minLat, maxLat, minLng, maxLng, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //Properties within certain radius
    @GetMapping("/radius")
    public ResponseEntity<PropertyResponse> getPropertiesWithinRadius(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam double radiusInKm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        PropertyResponse response = propertyService.filterByDistance(lat, lng, radiusInKm, page, size, sortBy, sortOrder);
        return ResponseEntity.ok(response);
    }


    //Clusters
    @GetMapping("/clusters")
    public ResponseEntity<List<ClusterDTO>> getClusters(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam double radius,
            @RequestParam(defaultValue = "0.05") double gridSize) {
        return ResponseEntity.ok(propertyService.getClusters(lat, lng, radius, gridSize));
    }



    //Polygon search
    @PostMapping("/polygon-search")
    public ResponseEntity<List<PropertyDTO>> getByPolygon(@RequestBody PolygonSearchDTO polygonSearchDTO) {
        List<PropertyDTO> properties = propertyService.findPropertiesWithinPolygon(polygonSearchDTO);
        return ResponseEntity.ok(properties);
    }

    //Sort by distance
    @GetMapping("/sort-by-distance")
    public ResponseEntity<List<PropertyDTO>> sortByDistance(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(propertyService.getPropertiesSortedByDistance(lat, lng, limit));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProperty(@RequestBody Property property) {
        try {
            Property savedProperty = propertyService.saveProperty(property);
            return ResponseEntity.ok(savedProperty);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to save property: " + e.getMessage());
        }
    }

    @GetMapping("/saved")
    public List<Property> getAllSavedProperties() {
        return propertyRepository.findAll();
    }






}
