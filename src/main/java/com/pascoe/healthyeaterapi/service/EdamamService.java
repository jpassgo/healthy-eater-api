package com.pascoe.healthyeaterapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.pascoe.healthyeaterapi.client.EdamamClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class EdamamService {

    private EdamamClient edamamClient;
    @Value("${app-id}") private String appId;
    @Value("${app-key}") private String appKey;

    public Optional<JsonNode> getCalories(String food) {
        return Optional.of(
                edamamClient.getFoodDetails(appId, appKey, food)
                        .get("parsed")
                        .get(0)
                        .get("food")
                        .get("nutrients")
                        .get("ENERC_KCAL"));
    }
}
