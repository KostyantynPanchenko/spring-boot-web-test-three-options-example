package com.example.web.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Another useful approach is to not start the server at all but to test only the layer below that,
 * where Spring handles the incoming HTTP request and hands it off to your controller. That way,
 * almost of the full stack is used, and your code will be called in exactly the same way as if it
 * were processing a real HTTP request but without the cost of starting the server. To do that, use
 * Spring’s MockMvc and ask for that to be injected by using the @AutoConfigureMockMvc annotation.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class Case2HelloControllerWithFullContextAndWithoutServerTest {

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
