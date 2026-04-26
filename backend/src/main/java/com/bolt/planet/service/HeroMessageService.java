package com.bolt.planet.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class HeroMessageService {

    private static final Map<String, String> HERO_MESSAGES = Map.of(
            "Mete", "Mete, what a win! By skipping the car ride and choosing a scooter, you saved 120g of CO2. Instead of adding to traffic exhaust, you let the oaks in Kadriorg Park breathe purely today.",

            "Liis", "Incredible choice, Liis! Instead of a carbon-heavy car trip, your e-bike kept the Baltic breeze salty and clean. You personally saved 200g of CO2 from reaching our shores today!",

            "Jaan", "Small change, big impact! By opting for a scooter instead of a private car for your market run, you spared the city from unnecessary emissions. The community gardens are blooming brighter thanks to you.",

            "Anu", "Anu, you are a Lahemaa legend! Choosing two wheels over a car today saved a massive amount of carbon. You ensured the ancient pine forest stays as green as it was centuries ago.",

            "Erik", "Erik, you arrived like a guardian! By choosing a scooter instead of a car, you kept the air in Tallinn’s green corridors crisp. Every gram you saved is a victory for the birds nesting there.",

            "Kadri", "Eco-excellence, Kadri! Instead of adding to the campus traffic, your cycle ride kept the air vibrant. You’ve proven that we don’t need cars to keep our sanctuary thriving.",

            "Marko", "Marko, you've left a clean trail! By skipping the car for your tour, you protected the Soomaa bogs from pollutants. The forest floor is clearer today because you chose a greener path.",

            "Elena", "Elena, your cross-city ride was heroic! By saving so much CO2 compared to a car journey, you directly helped our migratory birds find a fresh resting place. You are the change we need!"
    );

    public String generateHeroMessage(String name) {
        return HERO_MESSAGES.getOrDefault(name,
                "Your journey made the Baltic air a little cleaner today! You are a true guardian of Estonian nature.");
    }
}