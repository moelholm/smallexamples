package com.moelholm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@ConfigurationProperties("my")
public class FinalProperties {

  // You are now in "on-your-own" territory :)

  // The Spring Boot reference manual doesn't suggest this idiom....

  private final List<String> messages = new LinkedList<>();

  public List<String> getMessages() {
    return messages;
  }

  // Again: don't do it.

}