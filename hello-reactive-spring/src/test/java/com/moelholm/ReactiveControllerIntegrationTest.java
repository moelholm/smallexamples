package com.moelholm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(value = "server.port=8080", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
public class ReactiveControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void sayHello_whenInvoked_thenReturnsHelloWorld() {

        String httpResponseBody = this.restTemplate.getForObject("/reactive-controller", String.class);

        assertThat(httpResponseBody, equalTo("Hello World"));
    }

}
