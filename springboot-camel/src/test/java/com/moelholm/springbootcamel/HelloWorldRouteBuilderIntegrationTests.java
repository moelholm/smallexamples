package com.moelholm.springbootcamel;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloWorldRouteBuilderIntegrationTests {

  @Produce(uri = HelloWorldRouteBuilder.ROUTE_URI)
  private ProducerTemplate producerTemplate;

  @Test
  public void whenGivenDuke_thenSetsGreetingInBody() {

    // Given
    String caller = "Duke";

    // When
    String body = producerTemplate.requestBody((Object) caller, String.class);

    // Then
    assertThat(body).isEqualTo("Hi Duke! You are so cool 😎");
  }

}
