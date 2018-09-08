package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GreeterService.class, GreeterDao.class})
public class SpringBootPoweredGreeterServiceTests {

  @Autowired
  private GreeterService greeterService;

  // Spring Boot to the rescue! No attempts at wiring "AnnoyingBean" INTO "GreeterDao".
  // and no attempts at running the @PostConstruct method.

  @MockBean
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

}
