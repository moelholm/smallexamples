package com.moelholm;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

  @Override
  public void configure() {

    onException(NullPointerException.class)
        .handled(true)
        .log("Yepeee a NullPointerException")
        .end();

    onException(IllegalArgumentException.class)
        .handled(false)
        .log("Oops an IllegalArgumentException")
        .end();

    from("direct:myRoute")

        .choice()
            .when(exchangeProperty("throwNullPointerException").isNotNull())
                .throwException(NullPointerException.class, "msg")
            .endChoice()

            .when(exchangeProperty("throwIllegalArgumentException").isNotNull())
                .throwException(IllegalArgumentException.class, "msg2")
            .endChoice()
        .end()

        .process(exchange -> System.out.println("msg3"));
  }
}
