package com.bolt.planet.controller;

import com.bolt.planet.model.CalculationResult;
import com.bolt.planet.model.Persona;
import com.bolt.planet.repository.PersonaRepository;
import com.bolt.planet.service.AIService;
import com.bolt.planet.service.EmissionCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoltPlanetController {

    private final PersonaRepository personaRepository;
    private final EmissionCalculatorService emissionCalculatorService;
    private final AIService aiService;

    public BoltPlanetController(PersonaRepository personaRepository,
                                EmissionCalculatorService emissionCalculatorService,
                                AIService aiService) {
        this.personaRepository = personaRepository;
        this.emissionCalculatorService = emissionCalculatorService;
        this.aiService = aiService;
    }

    @GetMapping("/personas")
    public List<Persona> getPersonas() {
        return personaRepository.findAll();
    }

    @PostMapping("/calculate/{id}")
    public ResponseEntity<CalculationResult> calculate(@PathVariable int id) {
        return personaRepository.findById(id)
                .map(persona -> {
                    double savingsGrams = emissionCalculatorService.computeSavingsGrams(persona);
                    String heroMessage  = aiService.generateHeroMessage(
                            persona.getName(),
                            persona.getVehicleType(),
                            persona.getDistanceKm(),
                            savingsGrams
                    );
                    CalculationResult result = emissionCalculatorService.calculate(persona, heroMessage);
                    return ResponseEntity.ok(result);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
