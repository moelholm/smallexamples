package com.moelholm.wildfly.learning.multideployment;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class BusinessServiceBean {

    private final static Logger logger = LoggerFactory.getLogger(BusinessServiceBean.class);

    @EJB//(lookup = "java:global/myapp/myejbmodule/AnotherBusinessServiceBean")
    private AnotherBusinessServiceBean anotherBusinessServiceBean;

    @PostConstruct
    public void postConstruct() {
        logger.info("Created in classloader = [{}]", getClass().getClassLoader());
        logger.info("Initial greeting is: {}", anotherBusinessServiceBean.sayHello(getClass().getSimpleName()));
    }

    public String sayHello(String caller) {
        return anotherBusinessServiceBean.sayHello(caller);
    }
}
