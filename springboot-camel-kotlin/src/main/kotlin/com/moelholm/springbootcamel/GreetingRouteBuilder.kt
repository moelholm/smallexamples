package com.moelholm.springbootcamel

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class GreetingRouteBuilder : RouteBuilder() {

    override fun configure() {

        from(ROUTE_URI)

            .setBody().simple("{{app.greeting.message}}")

            // Kotlin and Camel both love the $ character: So help Kotlin
            // know that it shouldn't attempt to process it
            .setBody().simple("\$simple{body}")

            .to("log:/dev/null")

    }

    companion object {
        const val ROUTE_URI = "direct:GreetingRouteBuilder"
    }
}
