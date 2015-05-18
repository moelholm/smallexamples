package com.moelholm.wildfly.learning.singletons;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.moelholm.importantstuff.BusinessServiceGreetingBean;
import com.moelholm.importantstuff.PresentationGreetingBean;
import com.moelholm.interceptorsupport.MethodInvocationNotifier;
import com.moelholm.profiling.Service;
import com.moelholm.profiling.timingsupport.MethodExecutionLogger;

@RunWith(WildFlyEmbeddedArquillianRunner.class)
public class MethodTracerIntegrationTest {

    @EJB
    private PresentationGreetingBean helloBean;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class) //
                .addClass(WildFlyEmbeddedArquillianRunner.class)//
                .addPackage(BusinessServiceGreetingBean.class.getPackage()) //
                .addPackage(Service.class.getPackage()) //
                .addPackage(MethodExecutionLogger.class.getPackage())//
                .addPackage(MethodInvocationNotifier.class.getPackage());
    }

    @Test
    public void sayHello_whenGivenDuke_thenGreetsDuke() {

        // When
        String greeting = helloBean.sayHello("duke");

        // Then
        assertEquals("Mjello, dukeMjello, duke", greeting);
    }
}
