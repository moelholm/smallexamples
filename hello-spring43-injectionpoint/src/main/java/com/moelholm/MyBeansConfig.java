package com.moelholm;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyBeansConfig {

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public String greeting(InjectionPoint injectionPoint) {
    return "Hello World";
  }

}
