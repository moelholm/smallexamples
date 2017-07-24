package com.moelholm.async.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageRestControllerIntegrationTests {

  @Rule public OutputCapture outputCapture = new OutputCapture();

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void list_whenInvoked_thenMdcDataIsTransferredToAsyncMethod() {

    // Given
    //   We have activated the MdcFilter and the MdcTaskDecorator...

    // When
    restTemplate.getForObject("/messages", String[].class);

    // Then
    //   We expected MDC data to appear on each log line - even from @Async methods...

    //    - Logged from the Web thread:
    assertThat(outputCapture.toString())
        .containsPattern(
            "\\[userId:Duke\\] INFO.*? c.m.async.demo.MessageRestController     : RestController in action");

    //    - Logged from the @Async thread:
    assertThat(outputCapture.toString())
        .containsPattern(
            "\\[userId:Duke\\] INFO.*? c.moelholm.async.demo.MessageRepository  : Repository in action");
  }

  @Test
  public void list_whenInvoked_thenReturnsTwoMessages() {

    String[] messages = restTemplate.getForObject("/messages", String[].class);

    assertThat(messages).hasSize(2);
    assertThat(messages).contains("Hello World");
    assertThat(messages).contains("Spring Boot is awesome");
  }
}
