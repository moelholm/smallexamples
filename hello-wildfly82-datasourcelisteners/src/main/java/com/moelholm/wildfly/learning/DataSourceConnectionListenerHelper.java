package com.moelholm.wildfly.learning;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.jboss.jca.adapters.jdbc.spi.listener.ConnectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceConnectionListenerHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConnectionListenerHelper.class);

    public void registerDataSourceConnectionListener(DataSource ds) {
        setConnectionListener(ds, com.moelholm.wildfly.learning.DataSourceConnectionListener.class);
    }

    public void unregisterDataSourceConnectionListener(DataSource ds) {
        setConnectionListener(ds, null);
    }

    private void setConnectionListener(DataSource ds, Class<? extends ConnectionListener> connectionListenerClass) {
        try {
            Field mcfField = getFieldMcf(ds);
            Object managedConnectionFactory = mcfField.get(ds);
            LOG.info("Found: {}", managedConnectionFactory);

            Field conListenerClassNameField = getFieldConnectionListenerClassName(managedConnectionFactory);
            Object connectionListener = conListenerClassNameField.get(managedConnectionFactory);

            if (connectionListenerClass != null) {
                LOG.info("Found existing {} class: {}", ConnectionListener.class.getSimpleName(), connectionListener);
            }

            setDataSourceConnectionListener(managedConnectionFactory, conListenerClassNameField, connectionListenerClass);
            LOG.info("JDBC ConnectionListener {}registered", connectionListenerClass == null ? "un" : "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setDataSourceConnectionListener(Object managedConnectionFactory, Field conListenerClassNameField, Class<? extends ConnectionListener> connectionListenerClass)
            throws IllegalAccessException {
        conListenerClassNameField.set(managedConnectionFactory, (connectionListenerClass == null) ? null : connectionListenerClass.getName());
    }

    private Field getFieldConnectionListenerClassName(Object managedConnectionFactory) throws NoSuchFieldException {
        Field conListenerClassNameField = managedConnectionFactory.getClass().getSuperclass().getSuperclass().getDeclaredField("connectionListenerClassName");
        if (!conListenerClassNameField.isAccessible()) {
            conListenerClassNameField.setAccessible(true);
        }
        return conListenerClassNameField;
    }

    private Field getFieldMcf(DataSource ds) throws NoSuchFieldException {
        Field mcfField = ds.getClass().getDeclaredField("mcf");
        if (!mcfField.isAccessible()) {
            mcfField.setAccessible(true);
        }
        return mcfField;
    }
}