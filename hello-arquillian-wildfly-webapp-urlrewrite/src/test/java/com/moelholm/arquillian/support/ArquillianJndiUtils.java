package com.moelholm.arquillian.support;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ArquillianJndiUtils {
    
    @SuppressWarnings("unchecked")
    public static <T> T lookup(String jndiName) {
        T ejb = null;
        try {
            ejb = (T) new InitialContext().lookup(jndiName);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return ejb;
    }
}