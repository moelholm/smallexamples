package com.moelholm.wildfly.learning.singletons;

public class HelloAbstractConcurrencyBean {

    // This method has implicit @Singleton EJB WRITE lock (when inherited from, by a @Singleton component):

    public String formatCaller(String caller) {
        return String.format("!![%s]!!", caller);
    }

}
