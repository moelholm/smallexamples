package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreeterServiceIntegrationTests {

  @Autowired
  private GreeterService greeterService;

  @Test
  public void sayHello_whenInvokedWithDuke_thenSaysHelloWorldDuke() {

    // When
    String greeting = greeterService.sayHello("Duke");

    // Then
    assertThat(greeting).isEqualTo("Hello World, Duke");

  }

}