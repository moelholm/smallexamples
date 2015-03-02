package com.moelholm.wildfly.learning.singletons;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WildFlyEmbeddedArquillianRunner.class)
public class HelloConcurrencyBeanIntegrationTest {

    @EJB
    private HelloConcurrencyBean helloBean;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class) //
                .addClass(WildFlyEmbeddedArquillianRunner.class)//
                .addClass(HelloAbstractConcurrencyBean.class) //
                .addClass(HelloConcurrencyBean.class) //
                .addClass(HelloConcurrencyReadLockFriendBean.class);
    }

    @Test
    public void implicit_readLock_upgradeTo_writeLock() {

        String greeting = helloBean.sayHelloUsingReadLockFriend("duke");

        assertEquals("Hello !![duke]!!", greeting);
    }
}
