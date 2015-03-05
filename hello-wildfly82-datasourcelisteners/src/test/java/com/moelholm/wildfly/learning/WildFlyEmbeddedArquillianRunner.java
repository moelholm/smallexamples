package com.moelholm.wildfly.learning;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runners.model.InitializationError;

public class WildFlyEmbeddedArquillianRunner extends Arquillian {

    // hack hack hack

    static {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
    }

    public WildFlyEmbeddedArquillianRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

}
