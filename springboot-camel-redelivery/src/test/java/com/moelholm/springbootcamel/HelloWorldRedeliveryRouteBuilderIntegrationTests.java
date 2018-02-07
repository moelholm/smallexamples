package com.moelholm.springbootcamel;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloWorldRedeliveryRouteBuilderIntegrationTests {

  @Produce(uri = HelloWorldRedeliveryRouteBuilder.ROUTE_URI)
  private ProducerTemplate producerTemplate;

  @Autowired
  private CamelFriend camelFriend;

  @Test
  public void redelivery_resumesFromPointOfFailure() {

    // Given
    doThrow(new RuntimeException("Oh no!")).when(camelFriend).throwException();

    // When
    producerTemplate.send(new DefaultExchange(new DefaultCamelContext()));

    // Then
    verify(camelFriend, times(1)).before();
    verify(camelFriend, times(3)).throwException();
    verify(camelFriend, times(0)).after();
  }

  @TestConfiguration
  static class Config {

    @Bean
    CamelFriend camelFriend() {
      return mock(CamelFriend.class);
    }
  }

}
