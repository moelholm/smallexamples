package com.moelholm.interceptorsupport;

import java.lang.reflect.Method;

public interface MethodInvocationListener {
    
    void traceStarted(Method method);

    void methodEntered(Method method);
    
    void methodExited(Method method);
    
    void traceStopped(Method method);
}
