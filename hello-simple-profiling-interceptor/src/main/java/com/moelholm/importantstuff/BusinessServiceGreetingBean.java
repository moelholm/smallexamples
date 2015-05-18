package com.moelholm.importantstuff;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.moelholm.profiling.LogMethodExecutionTime;

@Stateless
public class BusinessServiceGreetingBean {

    @Inject
    private GreetingRepositoryBean greetingRepositoryBean;

    @LogMethodExecutionTime
    public String sayHello(String caller) {

        String greeting = greetingRepositoryBean.getGreeting(caller);

        String greeting2 = greetingRepositoryBean.getGreeting(caller);

        greetingRepositoryBean.getGreeting(caller);
        
        greetingRepositoryBean.getGreeting(caller);

        return greeting + greeting2;
    }

}
