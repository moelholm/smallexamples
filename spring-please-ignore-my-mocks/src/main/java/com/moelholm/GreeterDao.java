package com.moelholm;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class GreeterDao {

  // Spring will attempt to inject this bean even though our test
  // class creates a mock of GreeterDao:

  @Autowired
  private AnnoyingBean annoyingBean;

  // ... That is because the mock still has this field (by inheriteance;
  //  the mock is a subclass of GreeterDao) and because the mock is treated
  //  as a normal Spring bean - effectively making Spring initializing it.


  // Spring will attempt to invoke this lifecycle method as well:

  @PostConstruct
  private void explodeOnStartup() {
    throw new RuntimeException("Oh no !");
  }

  public String findGreeting() {
    return "Hello world, %s";
  }
}
