package com.moelholm;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.reactive.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.web.client.reactive.ClientWebRequestBuilders.get;
import static org.springframework.web.client.reactive.ResponseExtractors.body;

@RestController
public class ReactiveController {

    @RequestMapping("/reactive-controller")
    public Mono<String> sayHello() {
        WebClient reactiveHttpClient = new WebClient(new ReactorClientHttpConnector());
        return reactiveHttpClient
                .perform(get("http://localhost:8080/traditional-controller").accept(MediaType.TEXT_PLAIN))
                .extract(body(String.class));

    }

}
