package com.moelholm.wildfly.learning;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

public class CdiResources {

    @Produces
    @PersistenceContext(unitName = "ExamplePU")
    EntityManager em;

    @Produces
    @Resource
    DataSource ds;
}
