package com.wealthmap.wealthmap_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClusterDTO {
    private double latitude;
    private double longitude;
    private long count;
    private double avgValue;
    private double avgNetWorth;
}
