package com.moelholm;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class HelloBeforeAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) throws Throwable {
    if ("greet".equalsIgnoreCase(method.getName())) {
      args[0] = "Tux";
    }
  }

}
