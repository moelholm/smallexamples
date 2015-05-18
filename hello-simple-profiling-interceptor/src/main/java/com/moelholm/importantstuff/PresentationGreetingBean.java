package com.moelholm.importantstuff;

import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.moelholm.profiling.Service;

@Stateless
public class PresentationGreetingBean {

    @Inject
    private BusinessServiceGreetingBean serviceBean;
    
    @Inject
    private AuditLoggerBean auditLoggerBean;

    @Service
    public String sayHello(String caller) {

        String greeting = serviceBean.sayHello(caller);

        try {
            TimeUnit.MILLISECONDS.sleep(173);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        auditLoggerBean.auditLogStuff("Greeting about to be sent to caller");
        
        return greeting;
    }

}
