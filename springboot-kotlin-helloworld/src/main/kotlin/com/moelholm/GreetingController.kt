package com.moelholm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/hello/{name}")
    fun get(@PathVariable name: String) = "Hello, $name"

}