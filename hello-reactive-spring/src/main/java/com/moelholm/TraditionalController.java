package com.moelholm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraditionalController {

    @RequestMapping("/traditional-controller")
    public String sayHello() {
        return String.format("Hello World");
    }
}
