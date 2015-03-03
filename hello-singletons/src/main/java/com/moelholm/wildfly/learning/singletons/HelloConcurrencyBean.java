package com.moelholm.wildfly.learning.singletons;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@Lock(LockType.READ)
public class HelloConcurrencyBean extends HelloAbstractConcurrencyBean {

    @EJB
    private HelloConcurrencyFriendBean friendBean;

    public String sayHello(String caller) {

        // The following call actually performs a loopback call (re-entrant)
        // to the inherited 'format' method (which has .... Lock(WRITE))

        String greeting = friendBean.sayHello(caller);

        return greeting;
    }

}
