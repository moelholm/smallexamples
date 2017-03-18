package com.moelholm

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT
import org.springframework.test.context.junit4.SpringRunner
import kotlin.system.measureTimeMillis

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = DEFINED_PORT, value = "server.port=8090")
class MessageClientIntegrationTests {

    @Autowired
    lateinit var messageClient: MessageClient

    @Test
    fun `getMessages() should fetch messages in parallel`() {

        val durationInMilliseconds = measureTimeMillis {

            val messages = messageClient.getMessages()

            assertThat(messages.size).isEqualTo(2)
            assertThat(messages).extracting { it.id }.contains("1", "2")
            assertThat(messages).extracting { it.text }.contains("Hello World", "Hej Verden")

        }

        assertThat(durationInMilliseconds).isLessThan(5000)

    }

}