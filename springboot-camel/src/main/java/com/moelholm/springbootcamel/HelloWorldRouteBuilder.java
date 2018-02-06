package com.moelholm.springbootcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldRouteBuilder extends RouteBuilder {

  public static final String ROUTE_URI = "direct:helloWorldRouteBuilder";

  @Override
  public void configure() {

    from(ROUTE_URI)
        .setBody(simple("{{app.greeting.message}}"))
        .to("log:/dev/null");

  }
}
