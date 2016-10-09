package com.moelholm;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

import com.moelholm.Greeting.Language;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyBeanConfig {

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public String greeting(InjectionPoint ip) {

    Greeting greeting = findAnnotation(ip.getAnnotatedElement(), Greeting.class);

    return (Language.DA == greeting.language()) ? "Hej Verden" : "Hello World";
  }

}
