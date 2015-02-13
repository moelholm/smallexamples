package com.moelholm.wildfly.learning.multideployment;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.moelholm.arquillian.support.ArquillianJndiUtils;
import com.moelholm.arquillian.support.WildFlyEmbeddedArquillianRunner;

@RunWith(WildFlyEmbeddedArquillianRunner.class)
public class MultiDeploymentIntegrationTest {

    @Deployment(name = "myapp", order = 1)
    public static EnterpriseArchive createEar() {
        return ShrinkWrap.create(EnterpriseArchive.class, "myapp.ear") //
                .addAsModule( //
                        ShrinkWrap.create(JavaArchive.class, "myejbmodule.jar") //
                                .addClass(BusinessServiceBean.class) //
                                .addClass(AnotherBusinessServiceBean.class) //
                                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml") //
                );
    }

    @Deployment(name = "myweb", order = 2)
    public static WebArchive createJar() {
        return ShrinkWrap.create(WebArchive.class, "myweb.war") //
                .addClass(WildFlyEmbeddedArquillianRunner.class) //
                .addClass(ArquillianJndiUtils.class) //
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml") //
                .addAsManifestResource("jboss-deployment-structure.xml");
    }

    @EJB(lookup = "java:global/myapp/myejbmodule/BusinessServiceBean")
    private BusinessServiceBean ejb;

    @Test
    @OperateOnDeployment("myweb")
    public void sayHello_whenGivenDuke_thenReturnsHelloDuke() {
        assertEquals("Hello duke", ejb.sayHello("duke"));
    }
}
