package com.moelholm.springbootcamel;

import java.util.function.BiConsumer;
import org.apache.camel.ErrorHandlerFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RoutesDefinition;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldCustomDslRouteBuilder extends RouteBuilder {

  public static final String ROUTE_URI = "direct:HelloWorldCustomDslRouteBuilder";

  @Override
  public void configure() {

    setRouteCollection(new CustomDslRoutesDefinition());

    from(ROUTE_URI)
        .id(HelloWorldCustomDslRouteBuilder.class.getName())

        // Custom DSL:
        .logStarted()

        // (Standard builtin Camel DSL...)
        .setBody(constant("Hello World"))
        .bean(MyBean.class)

        // Custom DSL:
        .bean(MyBean.class, MyBean::dump)

        // Custom DSL:
        .logCompleted()

        .split(body())
            // Custom DSL trouble starts here...
            .bean(MyBean.class)
        .end();

  }

  public CustomDslRouteDefinition from(String uri) {
    return (CustomDslRouteDefinition) super.from(uri);
  }

  class CustomDslRouteDefinition extends RouteDefinition<CustomDslRouteDefinition> {

    CustomDslRouteDefinition logStarted() {
      log("Starting");
      return this;
    }

    CustomDslRouteDefinition logCompleted() {
      log("Completed");
      return this;
    }

    <Z extends Object, Y extends Object> CustomDslRouteDefinition bean(
        Class<?> clazz,
        BiConsumer<Z, Y> consumer) {

      process(exchange ->
          consumer.accept(
              (Z) getContext().getRegistry().findByType(clazz).iterator().next(),
              (Y) exchange.getIn().getBody()
          )
      );

      return this;
    }
  }

  public class CustomDslRoutesDefinition extends RoutesDefinition {

    @Override
    protected RouteDefinition createRoute() {
      RouteDefinition route = new CustomDslRouteDefinition();
      ErrorHandlerFactory handler = getErrorHandlerBuilder();
      if (handler != null) {
        route.setErrorHandlerBuilderIfNull(handler);
      }
      return route;
    }
  }
}
