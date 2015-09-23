package com.moelholm.arquillian.support;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class WildFlyEmbeddedArquillianRunner extends Arquillian {

    // hack hack hack

    static {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
    }

    public WildFlyEmbeddedArquillianRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

}
