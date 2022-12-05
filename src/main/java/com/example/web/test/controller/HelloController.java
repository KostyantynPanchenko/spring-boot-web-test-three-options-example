package com.example.web.test.controller;

import com.example.web.test.domain.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public Hello helloVisitor(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "greeting", required = false) String greeting) {
    if (name == null) {
      return new Hello("anonymous", "Hello");
    }
    if (greeting == null) {
      return new Hello(name, "Hello");
    }
    return new Hello(name, greeting);
  }
}
