package com.moelholm;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;

public class App {

  public static void main(String[] args) throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
      public void configure() {
        from("file:data/inbox?noop=true")
            .unmarshal()
            .json(JsonLibrary.Gson)
            .process(exchange -> {
              System.out.format("  %s%n", exchange.getIn().getBody());
            })
            .filter(body().convertToString().contains("uke"))
            .marshal()
            .json(JsonLibrary.Gson)
            .to("file:data/outbox");
      }
    });
    context.start();
    Thread.sleep(5000);
    context.stop();
  }

}
