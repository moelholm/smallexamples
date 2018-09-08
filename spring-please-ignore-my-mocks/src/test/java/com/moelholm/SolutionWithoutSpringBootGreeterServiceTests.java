package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.moelholm.SolutionWithoutSpringBootGreeterServiceTests.AwesomeTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AwesomeTestConfig.class)
public class SolutionWithoutSpringBootGreeterServiceTests {

  @Autowired
  private GreeterService greeterService;

  @Autowired
  private GreeterDao greeterDaoMock;

  @Test
  public void sayHello() {

    // Given
    when(greeterDaoMock.findGreeting()).thenReturn("Hola contigo, %s");

    // When
    String greeting = greeterService.sayHello("Duke");

    // Then
    assertThat(greeting).matches("Hola contigo, Duke");
  }

  // Spring can load this configuration, because it doesn't attempt
  // to wire "AnnoyingBean" INTO "GreeterDao".

  @Configuration
  static class AwesomeTestConfig {

    @Bean
    GreeterService greeterService() {
      return new GreeterService();
    }

    @Bean
    FactoryBean<GreeterDao> greeterDao() {
      // This is our collaborator that we just want to mock

      // ... spring will NOT post process its @Autowired
      // ... field and NOT invoke the @PostConstruct method,
      // ... because we use a BeanFactory to produce our bean

      return new AbstractFactoryBean<GreeterDao>() {
        @Override
        public Class<?> getObjectType() {
          return GreeterDao.class;
        }

        @Override
        protected GreeterDao createInstance() {
          return mock(GreeterDao.class);
        }
      };
    }
  }

}
