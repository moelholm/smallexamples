package com.moelholm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@ConfigurationProperties("my")
public class NormalProperties {

  private List<String> messages = new LinkedList<>();

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public List<String> getMessages() {
    return messages;
  }

}