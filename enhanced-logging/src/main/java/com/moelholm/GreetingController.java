package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private GreetingService greetingService;

    @RequestMapping("/greetings/{caller}")
    public String getGreeting(@PathVariable("caller") String caller) {

        LOG.info("Request received. PathVariable is: [{}]", caller);

        return greetingService.getGreeting(caller);
    }

}
