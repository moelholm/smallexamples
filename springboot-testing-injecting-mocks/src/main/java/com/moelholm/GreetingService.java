package com.moelholm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

  @Autowired
  private GreetingRepository greetingRepository;

  public String sayHello(String caller) {
    String greetingTemplate = greetingRepository.getDefaultGreeting();
    return String.format(greetingTemplate, caller);
  }
}
