package com.moelholm;

import static org.apache.camel.Exchange.EXCEPTION_CAUGHT;
import static org.apache.camel.builder.ExchangeBuilder.anExchange;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyRouteBuilderTests {

  @Autowired
  private CamelContext camelContext;

  @Produce(uri = "direct:myRoute")
  private ProducerTemplate producerTemplate;

  @Test
  public void whenExchangeExceptionIsNull_thenExceptionHasBeenHandled() {

    // Given
    String actionToDoInRoute = "throwNullPointerException";

    // When
    Exchange exchange = producerTemplate.send(anExchange(camelContext)
        .withProperty(actionToDoInRoute, "Did IQs just drop sharply while I was away?")
        .build());

    // Then
    // ...... An Exception HAS BEEN handled if doesn't have an Exception on the Exchange
    assertThat(exchange.getException()).isNull();
    // ...... But there is still evidence of it
    assertThat(exchange.getProperty(EXCEPTION_CAUGHT))
        .isExactlyInstanceOf(NullPointerException.class);
  }

  @Test
  public void whenExchangeExceptionIsNotNull_thenExceptionHasNotBeenHandled() {

    // Given
    String actionToDoInRoute = "throwIllegalArgumentException";

    // When
    Exchange exchange = producerTemplate.send(anExchange(camelContext)
        .withProperty(actionToDoInRoute, "Did IQs just drop sharply while I was away?")
        .build());

    // Then
    // ...... An Exception has NOT been handled if it is on the Exchange
    assertThat(exchange.getException()).isExactlyInstanceOf(IllegalArgumentException.class);
    //
    assertThat(exchange.getProperty(EXCEPTION_CAUGHT))
        .isExactlyInstanceOf(IllegalArgumentException.class);
  }

}
