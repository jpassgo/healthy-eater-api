package com.pascoe.healthyeaterapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.pascoe.healthyeaterapi.client.EdamamClient;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EdamamService {

  private final EdamamClient edamamClient;

  @Value("${app-id}")
  private String appId;

  @Value("${app-key}")
  private String appKey;

  public Optional<JsonNode> getCalories(String food) {
    return Optional.of(
        edamamClient
            .getFoodDetails(appId, appKey, food)
            .get("parsed")
            .get(0)
            .get("food")
            .get("nutrients")
            .get("ENERC_KCAL"));
  }

  public Optional<List<JsonNode>> getSimilarFoodOptions(String food) {
    return Optional.of(edamamClient.getFoodDetails(appId, appKey, food).findValues("label"));
  }
}
