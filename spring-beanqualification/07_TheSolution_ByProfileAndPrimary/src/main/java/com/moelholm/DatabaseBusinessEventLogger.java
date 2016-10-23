package com.moelholm;

import org.springframework.stereotype.Component;

@Component
public class DatabaseBusinessEventLogger implements BusinessEventLogger {

  @Override
  public void log(String messageTemplate, Object... args) {

    String message = String.format(messageTemplate, args);

    System.out.printf("[DATABASE] %s%n", message);

  }

}
