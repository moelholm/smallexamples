package com.moelholm.wildfly.learning.singletons;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class HelloConcurrencyReadLockFriendBean {

    @EJB
    private HelloConcurrencyBean helloConcurrencyBean;

    public String sayHello(String caller) {

        // Re-entrant method call to a WRITE lock method
        //
        // ( it has WRITE lock because it exists in the parent class of HelloConcurrencyBean and is not annotated with READ )
        //

        String formattedCaller = helloConcurrencyBean.formatCaller(caller);

        return String.format("Hello %s", formattedCaller);
    }

}
