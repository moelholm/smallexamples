package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GreetingRepository {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingRepository.class);

    public String lookupStandardGreeting() {

        LOG.info("Retrieving standard greeting from the \"datastore\"");

        return "Hello World";
    }

}
