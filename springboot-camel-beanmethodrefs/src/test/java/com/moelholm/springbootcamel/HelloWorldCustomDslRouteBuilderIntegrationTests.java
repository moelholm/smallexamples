package com.moelholm.springbootcamel;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloWorldCustomDslRouteBuilderIntegrationTests {

  @Produce(uri = HelloWorldCustomDslRouteBuilder.ROUTE_URI)
  private ProducerTemplate producerTemplate;

  @Test
  public void routeWithCustomDsl() {

    // Given

    // When
    producerTemplate.send(new DefaultExchange(new DefaultCamelContext()));

    // Then
  }

}
