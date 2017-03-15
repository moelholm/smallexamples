package com.moelholm

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.junit.Arquillian
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import javax.inject.Inject
import kotlin.reflect.KClass

@RunWith(Arquillian::class)
class HelloBeanIntegrationTests {

    @Inject
    lateinit var helloBean: HelloBean

    @Test
    fun sayHello_whenInvokedWithDuke_thenReturnsHelloDuke() {

        // Given
        val caller = "Duke"

        // When
        val message = helloBean.sayHello(caller)

        // Then
        assertEquals("Hello, Duke", message)

    }

    companion object {

        @JvmStatic
        @Deployment
        fun createDeployment() = ShrinkWrap.create(WebArchive::class.java)
                .addClass(HelloBean::class.java)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(File(KClass::class.java.protectionDomain.codeSource.location.file))

    }

}