package com.moelholm

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MessageControllerIntegrationTests {

    @Autowired lateinit
    var restTemplate: TestRestTemplate

    @Test
    fun `GET all messages`() {

        val messages = restTemplate.getForObject("/messages", Array<Message>::class.java)

        assertThat(messages).isNotEmpty()

    }

    @Test
    fun `GET a message`() {

        val message = restTemplate.getForObject("/messages/1", Message::class.java)

        assertThat(message.id).isEqualTo("1")
        assertThat(message.text).isEqualTo("Hello World")

    }

}