package com.moelholm;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/greetings/{caller}")
    public String getGreeting(@PathVariable("caller") String caller) {
        return String.format("Hello World, %s", caller);
    }

}
