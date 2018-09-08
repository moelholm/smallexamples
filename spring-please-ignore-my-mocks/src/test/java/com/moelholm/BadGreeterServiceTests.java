package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.moelholm.BadGreeterServiceTests.BadTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BadTestConfig.class)
public class BadGreeterServiceTests {

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

  // Spring cannot load this configuration, because it attempts
  // to wire "AnnoyingBean" INTO "GreeterDao".
  //
  // We don't want that ... because, hey, it is just a mock. And
  // the idea is that we want to isolate the dependency entirely...

  @Configuration
  static class BadTestConfig {

    @Bean
    GreeterService greeterService() {
      return new GreeterService();
    }

    @Bean
    GreeterDao greeterDao() {
      // This is our collaborator that we just want to mock

      // ... spring will post process GreeterDaos @Autowired
      // ... field, leading to the failure of this entire test suite

      // ... you can get pass this obstacle by using constructor injection

      // ... but even if you use constructor injection, the test suite still
      // ... still fails: because Spring calls the @PostConstruct method

      return mock(GreeterDao.class);
    }
  }

}
