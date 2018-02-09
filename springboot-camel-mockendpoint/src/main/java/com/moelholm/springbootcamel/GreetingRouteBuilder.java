package com.moelholm.springbootcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GreetingRouteBuilder extends RouteBuilder {

  public static final String ROUTE_URI = "direct:GreetingRouteBuilder";
  public static final String ROUTE_ID = "GreetingRouteBuilder";
  public static final String FIRST_PROPERTY = "firstProperty";
  public static final String SECOND_PROPERTY = "secondProperty";

  @Override
  public void configure() {

    from(ROUTE_URI)
        .id(ROUTE_ID)

        .setProperty("caller").body()

        // The MockEndpoint DO see this property
        .setProperty(FIRST_PROPERTY).simple("Hello $simple{exchangeProperty.caller}")

        // This is where the MockEndpoint gets injected
        .process(exchange -> System.out.println("I will never get printed"))

        // The MockEndpoint do NOT see this property
        .setProperty(SECOND_PROPERTY).simple("Goodbye $simple{exchangeProperty.caller}")

        .to("log:/dev/null");

  }

}
