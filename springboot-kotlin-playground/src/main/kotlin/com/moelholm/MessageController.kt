package com.moelholm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

@RestController
@RequestMapping("/messages")
class MessageController(val messageRepository: MessageRepository) {

    @PostConstruct
    fun postConstruct() = messageRepository.save(
            listOf(Message("1", "Hello World"), Message("2", "Hej Verden")))

    @GetMapping
    fun list() = messageRepository.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) = messageRepository.findOne(id)

}