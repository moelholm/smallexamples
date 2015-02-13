package com.moelholm.wildfly.learning.multideployment;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class AnotherBusinessServiceBean {

    private final static Logger logger = LoggerFactory.getLogger(AnotherBusinessServiceBean.class);

    @PostConstruct
    public void postConstruct() {
        logger.info("Created in classloader = [{}]", getClass().getClassLoader());
    }

    public String sayHello(String caller) {
        logger.info("Sending greeting to {} // classloader = [{}]", caller, getClass().getClassLoader());
        return String.format("Hello %s", caller);
    }
}
