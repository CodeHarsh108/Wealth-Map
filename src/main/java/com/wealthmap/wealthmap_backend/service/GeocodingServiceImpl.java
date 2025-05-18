package com.wealthmap.wealthmap_backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wealthmap.wealthmap_backend.dto.GeocodingResponseDTO;
import com.wealthmap.wealthmap_backend.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeocodingServiceImpl implements GeocodingService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public GeocodingResponseDTO geocodeAddress(String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);

        return parseGeocodeResponse(response);
    }

    @Override
    public GeocodingResponseDTO reverseGeocode(double lat, double lng) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);

        return parseGeocodeResponse(response);
    }

    private GeocodingResponseDTO parseGeocodeResponse(String response) {
        try {
            // Thing 1: Log full response
            System.out.println("Google API raw response: " + response);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode results = root.path("results");

            // Thing 3: Handle no results case gracefully instead of throwing
            if (!results.isArray() || results.isEmpty()) {
                GeocodingResponseDTO fallback = new GeocodingResponseDTO();
                fallback.setLatitude(0.0);
                fallback.setLongitude(0.0);
                fallback.setFormattedAddress("No matching address found.");
                return fallback;
            }

            JsonNode location = results.get(0).path("geometry").path("location");
            JsonNode formattedAddress = results.get(0).path("formatted_address");

            GeocodingResponseDTO dto = new GeocodingResponseDTO();
            dto.setLatitude(location.path("lat").asDouble());
            dto.setLongitude(location.path("lng").asDouble());
            dto.setFormattedAddress(formattedAddress.asText());
            return dto;

        } catch (Exception e) {
            // This handles actual parsing issues
            throw new RuntimeException("Error parsing geocoding response: " + e.getMessage());
        }
    }


}
