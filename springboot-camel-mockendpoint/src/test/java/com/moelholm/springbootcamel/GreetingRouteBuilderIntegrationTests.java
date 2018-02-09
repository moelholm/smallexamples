package com.moelholm.springbootcamel;

import static com.moelholm.springbootcamel.GreetingRouteBuilder.FIRST_PROPERTY;
import static com.moelholm.springbootcamel.GreetingRouteBuilder.SECOND_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Consumer;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GreetingRouteBuilderIntegrationTests {

  @Produce(uri = GreetingRouteBuilder.ROUTE_URI)
  private ProducerTemplate producerTemplate;

  @EndpointInject(uri = "mock:mockEndpoint")
  private MockEndpoint mockEndpoint;

  @Autowired
  private ModelCamelContext camelContext;

  @Test
  public void mockEndpoint_snapshotsTheExchange() throws Exception {

    // Given
    weaveRoute(GreetingRouteBuilder.ROUTE_ID, routeBuilder ->
        routeBuilder.weaveByToString(".*ocessor.*")
            .replace()
            .to(mockEndpoint));
    //     And
    Exchange inputExchange = new ExchangeBuilder(camelContext)
        .withBody("Duke")
        .build();

    // When
    producerTemplate.send(inputExchange);
    Exchange mockEndpointExchange = mockEndpoint.getExchanges().get(0);

    // Then
    assertThat(mockEndpointExchange.getExchangeId()).isEqualTo(inputExchange.getExchangeId());
    //     And
    assertThat(mockEndpointExchange.getProperty(FIRST_PROPERTY)).isEqualTo("Hello Duke");
    assertThat(mockEndpointExchange.getProperty(SECOND_PROPERTY)).isNull();
    //     And
    assertThat(inputExchange.getProperty(FIRST_PROPERTY)).isEqualTo("Hello Duke");
    assertThat(inputExchange.getProperty(SECOND_PROPERTY)).isEqualTo("Goodbye Duke");
    //     And
    assertThat(mockEndpointExchange).isNotSameAs(inputExchange);
  }

  private void weaveRoute(String id, Consumer<AdviceWithRouteBuilder> consumer) throws Exception {
    camelContext.getRouteDefinition(id)
        .adviceWith(camelContext, new AdviceWithRouteBuilder() {
          @Override
          public void configure() {
            consumer.accept(this);
          }
        });
  }

}
