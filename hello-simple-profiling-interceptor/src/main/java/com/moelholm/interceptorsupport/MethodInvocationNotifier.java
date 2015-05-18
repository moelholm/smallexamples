package com.moelholm.interceptorsupport;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class MethodInvocationNotifier {

    // We actually don't need an AtomicInteger , since this is a ThreadLocal
    // ... but it makes the calling code nicer , since we avoid get/set on the ThreadLocal
    private static final ThreadLocal<AtomicInteger> indent = new ThreadLocal<AtomicInteger>() {
        protected AtomicInteger initialValue() {
            return new AtomicInteger();
        };
    };

    private final MethodInvocationListener methodTraceListener;

    public MethodInvocationNotifier(MethodInvocationListener methodTraceListener) {
        this.methodTraceListener = methodTraceListener;
    }

    public void methodEntry(Method method) {
        int currentCallDepth = indent.get().getAndIncrement();

        if (currentCallDepth == 0) {
            methodTraceListener.traceStarted(method);
        }

        methodTraceListener.methodEntered(method);
    }

    public void methodExit(Method method) {
        int currentCallDepth = indent.get().decrementAndGet();

        if (currentCallDepth == 0) {
            indent.remove();
        }

        methodTraceListener.methodExited(method);

        if (currentCallDepth == 0) {
            methodTraceListener.traceStopped(method);
        }
    }

}