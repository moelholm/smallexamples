package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SlowGreetingController {

    private static final Logger LOG = LoggerFactory.getLogger(SlowGreetingController.class);

    @RequestMapping("/slowgreetings/{caller}")
    public String sayHelloWorldButLetItTake10seconds(@PathVariable("caller") String caller) {
        try {
            LOG.info("Sleeping 10 seconds...");
            TimeUnit.SECONDS.sleep(10);
            LOG.info("Sleeping 10 seconds...done");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return String.format("Hello World, %s (sorry that it had to take 10 seconds)", caller);
    }

}
