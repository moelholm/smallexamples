package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener {

    private static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOG.info("Session created : [{}]", se.getSession().getId());
        sessionRegistry.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOG.info("Session destroyed : [{}]", se.getSession().getId());
        sessionRegistry.removeSession(se.getSession());
    }
}
