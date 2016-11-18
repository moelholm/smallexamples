package com.moelholm;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

  private List<Greeting> greetings = new ArrayList<>();

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity post(@RequestBody Greeting greeting) {
    greetings.add(greeting.withId());
    URI location = URI.create(String.format("/greetings/%s", greeting.getId()));
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity get(@PathVariable String id) {
    Optional<Greeting> greeting = greetings.stream().filter(g -> id.equals(g.getId())).findAny();
    return greeting.isPresent() ? ResponseEntity.ok(greeting) : ResponseEntity.notFound().build();
  }

  @GetMapping
  public List<Greeting> getAll() {
    return greetings;
  }

}
