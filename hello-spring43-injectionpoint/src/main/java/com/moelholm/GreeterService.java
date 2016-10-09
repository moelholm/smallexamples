package com.moelholm;

import com.moelholm.Greeting.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreeterService {

  @Autowired
  @Greeting(language = Language.EN)
  private String greeting;

  public String sayHello(String caller) {
    return String.format("%s, %s", greeting, caller);
  }

}
