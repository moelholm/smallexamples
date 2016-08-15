package com.moelholm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SlowGreetingControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void sayHelloWorldButLetItTake10seconds_whenInvoked_thenReturnsGreeting() {
        // Given
        String url = "/slowgreetings/duke";

        // When
        String greetingFromSlowResponseController = restTemplate.getForObject(url, String.class);

        // Then
        assertThat(greetingFromSlowResponseController, startsWith("Hello World"));
    }

}
