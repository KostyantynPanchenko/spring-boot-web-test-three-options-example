package com.example.web.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetAll() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(
            """
                      [
                        {\"id\": 23,\"name\": \"MJ\",\"role\": \"Shooting Guard\"},
                        {\"id\": 33,\"name\": \"Pip\",\"role\": \"Forward\"}
                      ]
                      """));
  }

  @Test
  void testGetOne() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employees/23"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("{\"id\": 23,\"name\": \"MJ\",\"role\": \"Shooting Guard\"}"));
  }

  @Test
  void testCreateEmployee() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/employees")
            .contentType("application/json")
            .content("{\"id\": 24,\"name\": \"Kobe\",\"role\": \"Shooting Guard\"}"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().json("{\"id\": 1,\"name\": \"Kobe\",\"role\": \"Shooting Guard\"}"));

    mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNoContent());

    mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void testReplaceEmployee() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put("/employees/23")
            .content("{\"id\": 23,\"name\": \"MJ\",\"role\": \"Forward\"}")
            .contentType("application/json"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("{\"id\":23,\"name\":\"MJ\",\"role\":\"Forward\"}"));

    mockMvc.perform(MockMvcRequestBuilders.put("/employees/23")
            .content("{\"id\": 23,\"name\": \"MJ\",\"role\": \"Shooting Guard\"}")
            .contentType("application/json"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("{\"id\":23,\"name\":\"MJ\",\"role\":\"Shooting Guard\"}"));
  }

  @Test
  void testDeleteEmployee() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/employees")
            .contentType("application/json")
            .content("{\"id\": 24,\"name\": \"Kobe\",\"role\": \"Shooting Guard\"}"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().json("{\"id\": 1,\"name\": \"Kobe\",\"role\": \"Shooting Guard\"}"));

    mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isNoContent());

    mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void testException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/employees/34"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
