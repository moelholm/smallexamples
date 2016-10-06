package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GreeterServiceIntegrationTests {

  @Autowired
  private GreeterService greeterService;

  @Test
  public void greet_whenInvoked_thenActsWeird() {

    // Given
    String caller = "Duke";

    // When
    String greeting = greeterService.greet(caller);

    // Then
    assertThat(greeting).isEqualTo("Hello world, Tux");

  }


}
