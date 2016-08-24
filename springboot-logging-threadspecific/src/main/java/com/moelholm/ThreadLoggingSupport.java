package com.moelholm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ThreadLoggingSupport {

    private static final Map<Long, Boolean> THREAD_TO_ENABLED = new HashMap<>();

    public static void logEverything(boolean enabled) {
        THREAD_TO_ENABLED.put(Thread.currentThread().getId(), enabled);
    }

    public static boolean shouldLogEverything() {
        return Optional.ofNullable(THREAD_TO_ENABLED.get(Thread.currentThread().getId()))
                .orElse(false);
    }

    public static void cleanup() {
        THREAD_TO_ENABLED.remove(Thread.currentThread().getId());
    }
}
