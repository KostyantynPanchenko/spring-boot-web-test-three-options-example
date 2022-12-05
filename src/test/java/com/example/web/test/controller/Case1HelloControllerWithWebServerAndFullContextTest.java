package com.example.web.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.web.test.domain.Hello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Case1HelloControllerWithWebServerAndFullContextTest {

  @LocalServerPort
  private int localPort;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void testHelloVisitor() {
    ResponseEntity<Hello> entity = testRestTemplate.getForEntity(
        "http://localhost:" + localPort + "/hello?name=Stan&greeting=Hello", Hello.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(entity.getBody()).isNotNull();
    assertThat(entity.getBody().name()).isEqualTo("Stan");
    assertThat(entity.getBody().greeting()).isEqualTo("Hello");
  }
}
