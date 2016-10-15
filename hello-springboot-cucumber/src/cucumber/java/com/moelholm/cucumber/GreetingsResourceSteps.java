package com.moelholm.cucumber;

import static org.assertj.core.api.Assertions.assertThat;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration // Don't ask
public class GreetingsResourceSteps {

  @Autowired
  protected TestRestTemplate restTemplate;

  private String caller; // input

  private ResponseEntity<String> response; // output

  @Given("I use the caller (.*)")
  public void useCaller(String caller) {
    this.caller = caller;
  }

  @When("I request a greeting")
  public void requestGreeting() {
    response = restTemplate
        .exchange("/greetings/{caller}", HttpMethod.GET, null, String.class, caller);
  }

  @When("I should get response with HTTP status code (.*)")
  public void shouldGetResponseWithHttpStatusCode(int statusCode) {
    assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
  }

  @And("It should contain the message (.*)")
  public void shouldContainTheMessage(String message) {
    assertThat(response.getBody()).isEqualTo(message);
  }

}