package com.moelholm;

import org.springframework.beans.factory.annotation.Autowired;

public class GreeterDao {

  @Autowired
  private AnnoyingBean annoyingBean;

  public String findGreeting() {
    return "Hello world, %s";
  }
}
