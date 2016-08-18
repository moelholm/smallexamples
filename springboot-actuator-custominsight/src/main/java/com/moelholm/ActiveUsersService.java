package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class ActiveUsersService {

    private final static Logger LOG = LoggerFactory.getLogger(ActiveUsersService.class);

    private Set<String> activeUsers = new ConcurrentSkipListSet<>();

    public void userLoggedIn(String user) {
        activeUsers.add(user);
        LOG.info("User logged in: [{}]", user);
    }

    public void userLoggedOut(String user) {
        activeUsers.remove(user);
        LOG.info("User logged out: [{}]", user);
    }

    public List<String> listActiveUsers() {
        return new ArrayList<>(activeUsers);
    }
}
