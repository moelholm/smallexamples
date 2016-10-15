package com.moelholm.spring43.customannotations;

import javax.validation.constraints.NotNull;

@BusinessService
public class GreeterService {

  @LocalizedMessage("greeterservice.greeting")
  private Message greetingMsg;

  public String sayHello(@NotNull String caller) {
    return greetingMsg.format(caller);
  }

}
