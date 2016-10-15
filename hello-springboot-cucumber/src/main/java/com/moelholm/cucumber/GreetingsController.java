package com.moelholm.cucumber;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

  @GetMapping("/greetings/{caller}")
  public ResponseEntity<?> getGreeting(@PathVariable String caller) {

    if ("0xCAFEBABE".equalsIgnoreCase(caller)) {
      return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    String greeting = String.format("Hello World, %s", caller);

    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }

}
