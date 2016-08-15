package com.moelholm;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String threadName = Thread.currentThread().getName();
        try {
            Thread.currentThread().setName(String.format(
                    "%1$s_[started:%2$s | user:%3$s | uri:%4$s]_%1$s",
                    threadName, timeNow(), user(), uri(request)));
            chain.doFilter(request, response);
        } finally {
            Thread.currentThread().setName(threadName);
        }
    }

    private String uri(ServletRequest request) {
        return ((HttpServletRequest) request).getRequestURI();
    }

    private String timeNow() {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    private String user() {
        return "johndoe";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
