package com.bolt.planet.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationResult {
    private String personaName;
    private String vehicleType;
    private double distanceKm;
    private double baselineGrams;
    private double actualGrams;
    private double savingsGrams;
    private double savingsKg;
    private String heroMessage;
}
