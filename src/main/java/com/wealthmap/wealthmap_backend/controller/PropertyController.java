package com.wealthmap.wealthmap_backend.controller;
import com.wealthmap.wealthmap_backend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // open for React frontend
public class PropertyController {

    private final PropertyService propertyService;


}
