package com.moelholm.springbootcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldRedeliveryRouteBuilder extends RouteBuilder {

  public static final String ROUTE_URI = "direct:HelloWorldRedeliveryRouteBuilder";

  @Autowired
  private CamelFriend camelFriend;

  @Override
  public void configure() {

    // We want 3 delivery attempts in total (the first one, plus 2 re-deliveries)
    onException(Exception.class)
        .maximumRedeliveries(2);

    from(ROUTE_URI)

        // This step is invoked 1 time
        .process(exchange -> camelFriend.before())

        // This step is invoked 3 times
        .process(exchange -> camelFriend.throwException())

        // This step is invoked 0 times
        .process(exchange -> camelFriend.after())

        .to("log:/dev/null");

  }

}
