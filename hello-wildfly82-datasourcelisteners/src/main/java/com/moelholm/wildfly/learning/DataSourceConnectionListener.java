package com.moelholm.wildfly.learning;

import java.sql.Connection;
import java.sql.SQLException;

import org.jboss.jca.adapters.jdbc.spi.listener.ConnectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceConnectionListener implements ConnectionListener {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConnectionListener.class);

    @Override
    public void activated(Connection connection) throws SQLException {
        LOG.info("Connection activated {}", connection);
    }

    @Override
    public void passivated(Connection connection) throws SQLException {
        LOG.info("Connection passivated {}", connection);
    }

    @Override
    public void initialize(ClassLoader classLoader) throws SQLException {

        System.out.println("JdbcConnectionListener initialized");

    }

}
