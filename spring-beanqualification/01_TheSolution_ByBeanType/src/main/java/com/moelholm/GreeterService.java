package com.moelholm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreeterService {

  @Autowired
  private WebServiceBusinessEventLogger businessEventLogger;

  public String sayHello(String caller) {
    businessEventLogger.log("Sending hello message to %s", caller);
    return String.format("Hello World, %s", caller);
  }

}
