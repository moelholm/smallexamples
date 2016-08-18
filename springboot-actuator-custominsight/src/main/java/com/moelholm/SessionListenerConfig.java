package com.moelholm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@SuppressWarnings("Convert2Lambda")
public class SessionListenerConfig {

    @Autowired
    private ActiveUsersService activeUsersService;

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> userAuthenticated() {
        return new ApplicationListener<AuthenticationSuccessEvent>() {
            @Override
            public void onApplicationEvent(AuthenticationSuccessEvent event) {
                UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
                activeUsersService.userLoggedIn(userDetails.getUsername());
            }
        };
    }

    @Bean
    public ApplicationListener<SessionDestroyedEvent> sessionDestroyedListener() {
        return new ApplicationListener<SessionDestroyedEvent>() {
            @Override
            public void onApplicationEvent(SessionDestroyedEvent event) {
                UserDetails userDetails = (UserDetails) event.getSecurityContexts().stream()
                        .findFirst().get().getAuthentication().getPrincipal();
                activeUsersService.userLoggedOut(userDetails.getUsername());
            }
        };
    }

}
