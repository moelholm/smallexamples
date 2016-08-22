package com.moelholm;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class GreetingController {

    @RequestMapping(value = "/greetings/{caller}", produces = MediaType.TEXT_HTML_VALUE)
    public String getGreeting(@PathVariable("caller") String caller, HttpSession httpSession) {

        httpSession.setAttribute("invocationCount", 1 + Optional.ofNullable((Integer) httpSession.getAttribute("invocationCount")).orElse(0));
        httpSession.setAttribute("latestGreetingArgument", caller);

        return new StringBuilder()
                .append("<html><body>")
                .append("Your session id is: [").append(httpSession.getId()).append("]")
                .append("<br> ( Invocation count is : [").append(httpSession.getAttribute("invocationCount")).append("] )")
                .append("</body></html>")
                .toString();
    }

}
