package com.moelholm.spring43.customannotations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GreeterServiceIntegrationTests {

  @Autowired
  private GreeterService greeterService;

  @Test
  public void sayHello_whenInvoked_thenReturnsEnglishGreeting() {

    // Given
    String caller = "Duke";

    // When
    String greeting = greeterService.sayHello(caller);

    // Then
    assertThat(greeting).isEqualTo("Hello World, Duke");
  }

  @Test(expected = IllegalArgumentException.class)
  public void sayHello_whenInvokedWithNullArgument_thenThrowsIllegalArgumentException() {

    // Given
    String caller = null;

    // When
    String greeting = greeterService.sayHello(caller);

    // Then
    // ( kapOOOf )
  }

  @Test
  public void sayHello_whenLocaleIsDanish_andInvoked_thenReturnsDanishGreeting() {

    // Given
    LocaleContextHolder.setLocale(new Locale("da", "DK"));
    String caller = "Duke";

    // When
    String greeting = greeterService.sayHello(caller);

    // Then
    assertThat(greeting).isEqualTo("Hej Verden, Duke");
  }

  @After
  public void after() {
    LocaleContextHolder.setLocale(Locale.ENGLISH);
  }
}
