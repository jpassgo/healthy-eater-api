package com.pascoe.healthyeaterapi.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${feign.client.edamam-api.url}/", name = "edamam-api")
public interface EdamamClient {

  @RequestMapping(method = RequestMethod.GET)
  JsonNode getFoodDetails(
      @RequestParam(value = "app_id") String app_id,
      @RequestParam(value = "app_key") String app_key,
      @RequestParam(value = "ingr") String ingr);
}
