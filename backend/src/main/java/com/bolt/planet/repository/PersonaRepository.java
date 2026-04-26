package com.bolt.planet.repository;

import com.bolt.planet.model.Persona;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonaRepository {

    private static final List<Persona> PERSONAS = List.of(
            new Persona(1, "Mete",  "SCOOTER",  1.2,  "Quick office commute"),
            new Persona(2, "Liis",  "EBIKE",    4.5,  "Coastal ride"),
            new Persona(3, "Jaan",  "SCOOTER",  0.8,  "Quick market run"),
            new Persona(4, "Anu",   "EBIKE",   12.0,  "From outside Tallinn to center"),
            new Persona(5, "Erik",  "SCOOTER",  3.5,  "Meeting friends"),
            new Persona(6, "Kadri", "EBIKE",    7.2,  "To university campus"),
            new Persona(7, "Marko", "SCOOTER",  2.5,  "Telliskivi tour"),
            new Persona(8, "Elena", "EBIKE",   15.4,  "Crossing the city end to end")
    );

    public List<Persona> findAll() {
        return PERSONAS;
    }

    public Optional<Persona> findById(int id) {
        return PERSONAS.stream().filter(p -> p.getId() == id).findFirst();
    }
}
