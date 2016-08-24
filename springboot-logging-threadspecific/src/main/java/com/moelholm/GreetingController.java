package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static Logger LOG = LoggerFactory.getLogger(GreetingController.class);

    @RequestMapping(value = "/greetings/{caller}")
    public String getGreeting(@PathVariable("caller") String caller) {

        LOG.trace("Caller is: [{}]", caller);

        LOG.debug("Caller is: [{}]", caller);

        LOG.info("Caller is: [{}]", caller);

        LOG.warn("Caller is: [{}]", caller);

        LOG.error("Caller is: [{}]", caller);

        return String.format("Hello world, %s ( check out the logs. tip: invoke with query parameter trace=on/off )", caller);
    }

}
