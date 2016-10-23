package com.moelholm;

import org.springframework.stereotype.Repository;

@Repository
public class GreetingRepository {

  public String getDefaultGreeting() {
    return "Hello World, %s";
  }

}
