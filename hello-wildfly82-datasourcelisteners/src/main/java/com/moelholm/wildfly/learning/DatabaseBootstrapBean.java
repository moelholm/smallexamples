package com.moelholm.wildfly.learning;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class DatabaseBootstrapBean {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseBootstrapBean.class);

    @Resource
    private DataSource ds;

    @Inject
    private EntityManager em;

    @Inject
    private DataSourceConnectionListenerHelper connectionListenerHelper;

    @PostConstruct
    public void postConstruct() {
        LOG.info("Bootstrapping database...");

        Greeting le = new Greeting();

        le.setMessage("Hello World");

        em.persist(le);

        LOG.info("Bootstrapping database...done, ok!");

        LOG.info("Attaching JDBC connectionlistener...");

        connectionListenerHelper.registerDataSourceConnectionListener(ds);

        LOG.info("Attaching JDBC connectionlistener...done, ok!");
    }

    @PreDestroy
    public void preDestroy() {
        LOG.info("Detaching JDBC connectionlistener...");

        connectionListenerHelper.unregisterDataSourceConnectionListener(ds);

        LOG.info("Detaching  JDBC connectionlistener...done, ok!");
    }

}
