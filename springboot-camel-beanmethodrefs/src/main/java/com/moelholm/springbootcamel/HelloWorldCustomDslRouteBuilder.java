package com.moelholm.springbootcamel;

import java.util.function.BiConsumer;
import org.apache.camel.ErrorHandlerFactory;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RoutesDefinition;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldCustomDslRouteBuilder extends RouteBuilder {

  public static final String ROUTE_URI = "direct:HelloWorldCustomDslRouteBuilder";

  {
    setRouteCollection(new CustomDslRoutesDefinition());
  }

  // Hacks below....

  @Override
  public void configure() {

    from(ROUTE_URI)

        .setBody(simple("Hello World (body)"))
        .setProperty("myProp", simple("Hello World (property)"))

        // Type safe invocation of a bean method:
        .bean(MyBean.class, MyBean::methodWithOneArg)

        // ... essentially the same as this:
        .bean(MyBean.class, MyBean::methodWithOneArg, body())

        // ... but any expression will do:
        .bean(MyBean.class, MyBean::methodWithOneArg, simple("$simple{body}"))
        .bean(MyBean.class, MyBean::methodWithOneArg, exchangeProperty("myProp"))

        // Two parameter methods can also be invoked. Like this:
        .bean(MyBean.class, MyBean::methodWithTwoArgs, exchangeProperty("myProp"), body())

        .to("log:/dev/null");
  }

  public CustomDslRouteDefinition from(String uri) {
    return (CustomDslRouteDefinition) super.from(uri);
  }

  interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);
  }

  class CustomDslRouteDefinition extends RouteDefinition<CustomDslRouteDefinition> {

    // These methods supports single-arg bean method invocations...

    <Z extends Object, Y extends Object> CustomDslRouteDefinition bean(Class<?> clazz,
        BiConsumer<Z, Y> consumer) {
      return bean(clazz, consumer, body());
    }

    <Z extends Object, Y extends Object> CustomDslRouteDefinition bean(Class<?> clazz,
        BiConsumer<Z, Y> consumer, ValueBuilder valueBuilder) {
      return bean(clazz, consumer, valueBuilder.getExpression());
    }

    <Z extends Object, Y extends Object> CustomDslRouteDefinition bean(Class<?> clazz,
        BiConsumer<Z, Y> consumer, Expression expression) {

      process(exchange ->
          consumer.accept(
              (Z) getContext().getRegistry().findByType(clazz).iterator().next(),
              (Y) expression.evaluate(exchange, Object.class)
          )
      );

      return this;
    }

    // These methods supports two-arg bean method invocations...

    <Z extends Object, Y extends Object, A extends Object> CustomDslRouteDefinition bean(
        Class<?> clazz,
        TriConsumer<Z, Y, A> consumer
        , Expression expressionArgOne
        , Expression expressionArgTwo) {

      process(exchange ->
          consumer.accept(
              (Z) getContext().getRegistry().findByType(clazz).iterator().next(),
              (Y) expressionArgOne.evaluate(exchange, Object.class),
              (A) expressionArgTwo.evaluate(exchange, Object.class)
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
