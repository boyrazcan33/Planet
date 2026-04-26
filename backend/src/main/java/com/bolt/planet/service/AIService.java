package com.bolt.planet.service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    @Value("${anthropic.api.key}")
    private String apiKey;

    public String generateHeroMessage(String name, String vehicleType,
                                      double distanceKm, double savingsGrams) {
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .apiKey(apiKey)
                .build();

        String prompt = buildPrompt(name, vehicleType, distanceKm, savingsGrams);

        var message = client.messages().create(
                MessageCreateParams.builder()
                        .model("claude-sonnet-4-6")
                        .maxTokens(1024)
                        .addUserMessage(prompt)
                        .build()
        );

        return message.content().stream()
                .filter(block -> block.isText())
                .map(block -> block.text().get().text())
                .findFirst()
                .orElse("Your journey made the Baltic air a little cleaner today!");
    }

    private String buildPrompt(String name, String vehicleType,
                               double distanceKm, double savingsGrams) {
        return """
                You are a cheerful, nature-loving Carbon Coach built into the Bolt Planet plugin.

                A user just completed a trip:
                - Name: %s
                - Vehicle: %s
                - Distance: %.1f km
                - CO2 saved vs private car: %.1fg

                Write a unique, joyful 2-3 sentence hero message celebrating their savings.

                Rules:
                - Reference specific Estonian/Baltic nature: Lahemaa forests, Kadriorg park, Soomaa bogs, Baltic Sea, local birds and squirrels
                - Frame the user as a nature guardian
                - Never mention food, flights, or consumption rewards
                - Focus only on nature benefits: cleaner air, trees breathing easier, animals thriving
                - Never repeat the same metaphor across different personas
                - Each message must feel personal to this specific distance and vehicle type
                - Respond in English only
                """.formatted(name, vehicleType, distanceKm, savingsGrams);
    }
}
