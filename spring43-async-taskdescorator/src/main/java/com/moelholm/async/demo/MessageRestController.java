package com.moelholm.async.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageRestController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final MessageRepository messageRepository;

  MessageRestController(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @GetMapping
  List<String> list() throws Exception {
    logger.info("RestController in action");
    return messageRepository.findAll().get();
  }
}
