package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @MockBean
  private GreetingRepository greetingRepositoryForTest;

  @Test
  public void get_whenConsumed_thenReturnsGreeting() {

    // Given
    when(greetingRepositoryForTest.getDefaultGreeting()).thenReturn("Hej världen, %s");

    // When
    ResponseEntity<String> greetingResponse = restTemplate
        .getForEntity("/greetings/Duke", String.class);

    // Then
    assertThat(greetingResponse.getStatusCodeValue()).isEqualTo(200);
    assertThat(greetingResponse.getBody()).isEqualTo("Hej världen, Duke");
  }

}
