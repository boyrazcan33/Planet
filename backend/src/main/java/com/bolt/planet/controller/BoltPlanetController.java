package com.bolt.planet.controller;

import com.bolt.planet.model.CalculationResult;
import com.bolt.planet.model.Persona;
import com.bolt.planet.repository.PersonaRepository;
import com.bolt.planet.service.HeroMessageService;
import com.bolt.planet.service.EmissionCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoltPlanetController {

    private final PersonaRepository personaRepository;
    private final EmissionCalculatorService emissionCalculatorService;
    private final HeroMessageService heroMessageService;

    public BoltPlanetController(PersonaRepository personaRepository,
                                EmissionCalculatorService emissionCalculatorService,
                                HeroMessageService heroMessageService) {
        this.personaRepository = personaRepository;
        this.emissionCalculatorService = emissionCalculatorService;
        this.heroMessageService = heroMessageService;
    }

    @GetMapping("/personas")
    public List<Persona> getPersonas() {
        return personaRepository.findAll();
    }

    @PostMapping("/calculate/{id}")
    public ResponseEntity<CalculationResult> calculate(@PathVariable int id) {
        return personaRepository.findById(id)
                .map(persona -> {
                    String heroMessage = heroMessageService.generateHeroMessage(persona.getName());
                    CalculationResult result = emissionCalculatorService.calculate(persona, heroMessage);
                    return ResponseEntity.ok(result);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}