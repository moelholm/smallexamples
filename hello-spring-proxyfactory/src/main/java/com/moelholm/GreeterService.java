package com.moelholm;

import org.springframework.stereotype.Service;

@Service
public class GreeterService {

  public String greet(String caller) {
    return String.format("Hello world, %s", caller);
  }

}
