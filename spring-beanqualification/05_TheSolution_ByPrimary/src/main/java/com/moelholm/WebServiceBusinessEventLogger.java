package com.moelholm;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WebServiceBusinessEventLogger implements BusinessEventLogger {

  @Override
  public void log(String messageTemplate, Object... args) {

    String message = String.format(messageTemplate, args);

    System.out.printf("[WEBSERVICE] %s%n", message);

  }

}
