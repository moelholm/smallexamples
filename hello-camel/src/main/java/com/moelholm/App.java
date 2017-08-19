package com.moelholm;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class App {

  public static void main(String[] args) throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
      public void configure() {
        from("file:data/inbox?noop=true")
            .to("file:data/outbox");
      }
    });
    context.start();
    Thread.sleep(10000);
    context.stop();
  }

}
