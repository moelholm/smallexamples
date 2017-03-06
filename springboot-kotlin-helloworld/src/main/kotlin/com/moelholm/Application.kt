package com.moelholm

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Application {

    @RestController
    class HelloWorldController {

        @GetMapping("/hello/{name}")
        fun get(@PathVariable name: String) = "Hello, $name"

    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
