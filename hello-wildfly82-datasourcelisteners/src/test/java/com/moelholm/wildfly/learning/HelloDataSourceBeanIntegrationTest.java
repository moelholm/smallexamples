package com.moelholm.wildfly.learning;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WildFlyEmbeddedArquillianRunner.class)
public class HelloDataSourceBeanIntegrationTest {

    @EJB
    private HelloDataSourceBean helloBean;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class) //
                .addClass(WildFlyEmbeddedArquillianRunner.class) //
                .addPackage(HelloDataSourceBean.class.getPackage()) //
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml") //
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml") //
                .addAsWebInfResource("META-INF/jboss-deployment-structure.xml", "jboss-deployment-structure.xml");
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void getGreetingViaJdbc() {

        // Given
        // ( a properly configured EJB-JAR archive ;) )

        // When
        String greeting = helloBean.getGreetingViaJdbc();

        // Then
        assertEquals("Hello World", greeting);
    }

    @Test
    public void getGreetingViaJpa() {

        // Given
        // ( a properly configured EJB-JAR archive ;) )

        // When
        String greeting = helloBean.getGreetingViaJpa();

        // Then
        assertEquals("Hello World", greeting);

    }
}
