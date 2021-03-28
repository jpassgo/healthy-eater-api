package com.pascoe.healthyeaterapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascoe.healthyeaterapi.client.EdamamClient;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EdamamServiceTest {

  @Mock private EdamamClient edamamClient;

  @InjectMocks private EdamamService edamamService;

  String jsonString = "{ \"calories\": \"100.00\"}";

  @Test
  void getCalories() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    JsonNode node = mapper.readTree(jsonString);

    //         .get("parsed")
    //                .get(0)
    //                .get("food")
    //                .get("nutrients")
    //                .get("ENERC_KCAL"))

    when(edamamClient
            .getFoodDetails(anyString(), anyString(), anyString())
            .get("parsed")
            .get(0)
            .get("food")
            .get("nutrients")
            .get("ENERC_KCAL"))
        .thenReturn(node);

    Optional<JsonNode> calories = edamamService.getCalories("dog food");

    assertThat(calories).isNotNull();
  }
}
