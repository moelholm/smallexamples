package com.moelholm.springbootcamel;

import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Handler
  public void methodWithOneArg(String argOne) {
    logger.info("ArgOne [{}]", argOne);
  }

  public void methodWithTwoArgs(String argOne, String argTwo) {
    logger.info("ArgOne [{}] - ArgTwo [{}]", argOne, argTwo);
  }
}
