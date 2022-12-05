package com.example.web.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * We can narrow the tests to only the web layer by using @WebMvcTest - the least expensive test.
 */
@WebMvcTest
public class Case3HelloControllerWithWebLayerOnlyTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testHelloVisitor() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/hello?name=Stan&greeting=Hello"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("{\"name\": \"Stan\", \"greeting\": \"Hello\"}" ));
  }
}
