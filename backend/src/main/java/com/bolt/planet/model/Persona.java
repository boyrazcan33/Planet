package com.bolt.planet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Persona {
    private int id;
    private String name;
    private String vehicleType;
    private double distanceKm;
    private String scenario;
}
