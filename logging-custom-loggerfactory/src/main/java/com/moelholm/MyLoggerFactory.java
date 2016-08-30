package com.moelholm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates {@link Logger} objects.
 */
public class MyLoggerFactory {

    /**
     * @return a new {@link Logger} using the name of the caller class.
     */
    public static Logger getLogger() {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String callersClassName = stackTrace[2].getClassName();

        return LoggerFactory.getLogger(callersClassName);
    }

}
