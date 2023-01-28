package com.example.web.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.test.domain.Employee;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.ClassPathResource;

@JsonTest
public class SerializationTest {

  @Autowired
  private JacksonTester<Employee> json;
  private JsonContent<Employee> jsonContent;

  @BeforeEach
  void setUp() throws IOException {
    jsonContent = json.write(new Employee(23L, "MJ", "Shooting Guard"));
  }

  @Test
  void testSerializationBody() {
    assertThat(jsonContent.getJson()).isEqualToIgnoringWhitespace("""
        {
          "id": 23,
          "name": "MJ",
          "role": "Shooting Guard"
        }
        """);
  }
  @Test
  void testSerializationWithJsonPath() {
    assertThat(jsonContent).extractingJsonPathValue("$.id").isEqualTo(23);
    assertThat(jsonContent).extractingJsonPathStringValue("$.name").isEqualTo("MJ");
    assertThat(jsonContent).extractingJsonPathStringValue("$.role").isEqualTo("Shooting Guard");
  }

  @Test
  void testSerializationWithFile() {
    assertThat(jsonContent).isEqualToJson(new ClassPathResource("jordan.json"));
  }
}
