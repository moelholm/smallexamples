package com.moelholm.profiling;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.moelholm.interceptorsupport.MethodInvocationNotifier;
import com.moelholm.profiling.timingsupport.MethodExecutionLogger;

/**
 * 
 * <b>Note</b>: This is a priority interceptor (EAR-global, default enabled).
 * 
 * @author nickymolholm
 */
@Priority(0)
@Service
@Interceptor
public class ServiceInterceptor {

    private static final MethodInvocationNotifier METHOD_TRACER = new MethodInvocationNotifier(new MethodExecutionLogger());

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ctx) throws Exception {
        try {
            METHOD_TRACER.methodEntry(ctx.getMethod());
            return ctx.proceed();
        } finally {
            METHOD_TRACER.methodExit(ctx.getMethod());
        }
    }
}
