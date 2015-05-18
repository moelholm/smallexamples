package com.moelholm.profiling.timingsupport;

import java.lang.reflect.Method;

public final class MethodExecution {

    private final Method method;
    private final int callLevel;
    private final long startMilliseconds;
    private long stopMilliseconds;

    public MethodExecution(int callLevel, Method method) {
        this.callLevel = callLevel;
        this.method = method;
        this.startMilliseconds = System.currentTimeMillis();
    }

    public long getDuration() {
        return stopMilliseconds - startMilliseconds;
    }

    public void complete() {
        this.stopMilliseconds = System.currentTimeMillis();
    }

    public long getStartMilliseconds() {
        return startMilliseconds;
    }

    public long getStopMilliseconds() {
        return stopMilliseconds;
    }

    public int getCallLevel() {
        return callLevel;
    }

    public Method getMethod() {
        return method;
    }

}