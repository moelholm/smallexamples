package com.moelholm.wildfly.learning.singletons;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.IllegalLoopbackException;

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
                .addClass(HelloConcurrencyFriendBean.class);
    }

    @Test
    public void implicit_readLock_upgradeTo_writeLock() {

        try {

            helloBean.sayHelloUsingReadLockFriend("duke");

        } catch (EJBTransactionRolledbackException ere) {

            IllegalLoopbackException cause = (IllegalLoopbackException) ere.getCause();

            assertEquals("JBAS014370: EJB 3.1 PFD2 4.8.5.1.1 upgrading from read to write lock is not allowed", cause.getMessage());
        }

    }
}
