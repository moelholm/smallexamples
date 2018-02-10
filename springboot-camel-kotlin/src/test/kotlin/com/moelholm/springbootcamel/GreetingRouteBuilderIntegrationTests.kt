package com.moelholm.springbootcamel

import org.apache.camel.Produce
import org.apache.camel.ProducerTemplate
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class GreetingRouteBuilderIntegrationTests {

    @Produce(uri = GreetingRouteBuilder.ROUTE_URI)
    lateinit var producerTemplate: ProducerTemplate

    @Test
    fun `when given Kotlin then returns greeting`() {

        // Given
        val caller = "Kotlin"

        // When
        val body = producerTemplate.requestBody(caller as Any, String::class.java)

        // Then
        assertThat(body).isEqualTo("Hi Kotlin! You are so cool !")
    }

}
