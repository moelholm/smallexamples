package com.moelholm

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.container.test.api.RunAsClient
import org.jboss.arquillian.junit.Arquillian
import org.jboss.arquillian.test.api.ArquillianResource
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.net.URI
import javax.ws.rs.client.ClientBuilder
import kotlin.reflect.KClass


@RunWith(Arquillian::class)
class HelloResourceIntegrationTests {

    @ArquillianResource
    lateinit var url: URI

    @Test @RunAsClient
    fun get_whenInvokedWithDuke_thenReturnsHelloDuke() {

        // Given
        val caller = "Duke"

        // When
        val message = ClientBuilder.newClient().target(url)
                .path("/api/hello/$caller")
                .request()
                .get(String::class.java)


        // Then
        assertEquals("Hello, Duke", message)

    }

    companion object {

        @JvmStatic
        @Deployment
        fun createDeployment() = ShrinkWrap.create(WebArchive::class.java)
                .addPackage(HelloResource::class.java.`package`)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(File(KClass::class.java.protectionDomain.codeSource.location.file))

    }

}