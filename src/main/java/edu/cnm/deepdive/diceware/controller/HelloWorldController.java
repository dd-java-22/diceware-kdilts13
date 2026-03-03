package edu.cnm.deepdive.diceware.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public String get(@RequestParam(required = false, defaultValue = "World!") String name) {
    return "Hello, " + name;
  }
}
