package com.moelholm.wildfly.learning.multideployment;

import javax.ejb.Stateless;

@Stateless
public class HelloBean {

    public String sayHello(String caller) {
        return String.format("Hello %s", caller);
    }
}
