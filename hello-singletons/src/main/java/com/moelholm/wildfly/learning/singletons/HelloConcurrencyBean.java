package com.moelholm.wildfly.learning.singletons;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@Lock(LockType.READ)
public class HelloConcurrencyBean extends HelloAbstractConcurrencyBean {

    @EJB
    private HelloConcurrencyFriendBean readLockFriend;

    public String sayHelloUsingReadLockFriend(String caller) {
        
        String greeting = readLockFriend.sayHello(caller);

        return greeting;
    }

}
