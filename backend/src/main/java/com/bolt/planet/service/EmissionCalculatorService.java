package com.bolt.planet.service;

import com.bolt.planet.model.CalculationResult;
import com.bolt.planet.model.Persona;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmissionCalculatorService {

    private static final double BASELINE_G_PER_KM = 160.0;

    private static final Map<String, Double> VEHICLE_EMISSIONS = Map.of(
            "SCOOTER", 17.0,
            "EBIKE",   25.0
    );

    public double computeSavingsGrams(Persona persona) {
        double vehicleEmission = VEHICLE_EMISSIONS.get(persona.getVehicleType());
        return (BASELINE_G_PER_KM - vehicleEmission) * persona.getDistanceKm();
    }

    public CalculationResult calculate(Persona persona, String heroMessage) {
        double vehicleEmission = VEHICLE_EMISSIONS.get(persona.getVehicleType());
        double distanceKm     = persona.getDistanceKm();
        double baselineGrams  = BASELINE_G_PER_KM * distanceKm;
        double actualGrams    = vehicleEmission * distanceKm;
        double savingsGrams   = baselineGrams - actualGrams;

        return CalculationResult.builder()
                .personaName(persona.getName())
                .vehicleType(persona.getVehicleType())
                .distanceKm(distanceKm)
                .baselineGrams(baselineGrams)
                .actualGrams(actualGrams)
                .savingsGrams(savingsGrams)
                .savingsKg(savingsGrams / 1000.0)
                .heroMessage(heroMessage)
                .build();
    }
}
