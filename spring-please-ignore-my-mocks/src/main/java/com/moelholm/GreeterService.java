package com.moelholm;

import org.springframework.beans.factory.annotation.Autowired;

public class GreeterService {

  @Autowired
  private GreeterDao greeterDao;

  public String sayHello(String caller) {
    String greetingTemplate = greeterDao.findGreeting();
    return String.format(greetingTemplate, caller);
  }

}
