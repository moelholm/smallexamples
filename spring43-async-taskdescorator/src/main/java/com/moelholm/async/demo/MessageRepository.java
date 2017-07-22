package com.moelholm.async.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Repository
class MessageRepository {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Async
  Future<List<String>> findAll() {
    logger.info("Repository in action");
    return new AsyncResult<>(Arrays.asList("Hello World", "Spring Boot is awesome"));
  }
}
