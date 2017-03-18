package com.moelholm

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class MessageClient(templateBuilder: RestTemplateBuilder, @Value("\${server.port}") val port: Int) {

    val restTemplate = templateBuilder.rootUri("http://localhost:$port").build()

    fun getMessages(): List<Message> {

        fun asyncGetForObject(id: Int) = async(CommonPool) {
            TimeUnit.SECONDS.sleep(4)
            restTemplate.getForObject("/messages/$id/", Message::class.java)
        }

        val messages = listOf(asyncGetForObject(1), asyncGetForObject(2))

        return runBlocking { messages.map { it.await() } }

    }


}