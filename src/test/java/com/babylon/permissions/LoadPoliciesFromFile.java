package com.babylon.permissions;

import java.io.IOException;
import java.nio.charset.Charset;

import com.babylon.permissions.config.ObjectMapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;

import static com.google.common.io.Resources.getResource;

public class LoadPoliciesFromFile {

  private final ObjectMapper objectMapper = new ObjectMapperProvider().getMapper();

  @Test
  public void loadFromFile() throws IOException {
    String json = loadStringFromFile("policies/reviewer_policy.json");

  }

  private String loadStringFromFile(String fileName) throws IOException {
    return Resources.toString(getResource(fileName), Charset.defaultCharset());
  }
}
