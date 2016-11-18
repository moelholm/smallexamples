package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void post_whenInvoked_thenCreatesNewGreeting() {

    // Given
    JsonBuilderFactory jsonBuilderFactory = Json.createBuilderFactory(null);
    JsonObjectBuilder greeting = jsonBuilderFactory.createObjectBuilder();
    greeting.add("author", "Duke");
    greeting.add("message", "Java Rocks!");
    String json = greeting.build().toString();

    // When
    ResponseEntity<String> responseEntity = restTemplate
        .postForEntity("/greetings", createJsonHttpEntity(json), String.class);

    // Then
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    assertThat(responseEntity.getHeaders().getLocation().toString()).isEqualTo("/greetings/1");

  }

  private HttpEntity<String> createJsonHttpEntity(String json) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(json, httpHeaders);
  }

}