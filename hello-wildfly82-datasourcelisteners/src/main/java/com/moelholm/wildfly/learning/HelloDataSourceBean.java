package com.moelholm.wildfly.learning;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HelloDataSourceBean {

    private static final Logger LOG = LoggerFactory.getLogger(HelloDataSourceBean.class);

    @Inject
    private EntityManager em;

    @Inject
    private DataSource ds;

    @Resource
    private DataSource ds2;

    public String getGreetingViaJpa() {
        LOG.info("");
        LOG.info("getGreetingViaJpa()...");
        Greeting g = em.find(Greeting.class, 1L);
        LOG.info("getGreetingViaJpa()...done");
        LOG.info("");
        return g.getMessage();
    }

    public String getGreetingViaJdbc() {
        LOG.info("");
        LOG.info("getGreetingViaJdbc()...");

        final String sql = "select message from greeting";

        try (Connection con = ds.getConnection(); Statement s = con.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            return rs.next() ? rs.getString("message") : "";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            LOG.info("getGreetingViaJdbc()...done");
            LOG.info("");
        }
    }

}
