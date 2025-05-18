package com.wealthmap.wealthmap_backend.controller;

import com.wealthmap.wealthmap_backend.dto.GeocodingResponseDTO;
import com.wealthmap.wealthmap_backend.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geocoding")
@RequiredArgsConstructor
public class GeocodingController {

    private final GeocodingService geocodingService;



    @GetMapping("/address")
    public GeocodingResponseDTO geocodeAddress(@RequestParam String address) {
        return geocodingService.geocodeAddress(address);
    }

    @GetMapping("/coordinates")
    public GeocodingResponseDTO reverseGeocode(@RequestParam double lat, @RequestParam double lng) {
        return geocodingService.reverseGeocode(lat, lng);
    }
}
