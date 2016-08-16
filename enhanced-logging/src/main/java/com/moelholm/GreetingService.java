package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingService.class);

    @Autowired
    private GreetingRepository greetingRepository;

    public String getGreeting(String caller) {

        String standardGreeting = greetingRepository.lookupStandardGreeting();

        LOG.info("Formatting greeting for [{}]", caller);

        return String.format("%s, %s", standardGreeting, caller);
    }

}
