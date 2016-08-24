package com.moelholm;

public class ThreadLoggingSupport {

    private static ThreadLocal<Boolean> ENABLED = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    public static void logEverything(boolean enabled) {
        ENABLED.set(enabled);
    }

    public static boolean shouldLogEverything() {
        return ENABLED.get();
    }

    public static void cleanup() {
        ENABLED.remove();
    }
}
