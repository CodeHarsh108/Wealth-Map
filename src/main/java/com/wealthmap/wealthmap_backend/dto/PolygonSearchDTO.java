package com.wealthmap.wealthmap_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class PolygonSearchDTO {
    private List<Double[]> coordinates; // Each element: [longitude, latitude]
}
