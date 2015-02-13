package com.moelholm.wildfly.learning.multideployment;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.moelholm.arquillian.support.WildFlyEmbeddedArquillianRunner;

@RunWith(WildFlyEmbeddedArquillianRunner.class)
public class SingleDeploymentIntegrationTest {

    @EJB
    private BusinessServiceBean businessServiceBean;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class) //
                .addClass(WildFlyEmbeddedArquillianRunner.class) //
                .addClass(BusinessServiceBean.class) //
                .addClass(AnotherBusinessServiceBean.class) //
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void sayHello_whenGivenDuke_thenReturnsHelloDuke() {
        assertEquals("Hello duke", businessServiceBean.sayHello("duke"));
    }
}
