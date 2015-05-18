package com.moelholm.profiling.timingsupport;

import java.lang.reflect.Method;

import com.moelholm.interceptorsupport.MethodInvocationListener;

public class MethodExecutionLogger implements MethodInvocationListener {

    private static final MethodExecutionFormatter methodExecutionFormatter = MethodExecutionFormatter.create() //
            .withUnknownCodeBlocks()//
    ;

    private static final ThreadLocal<MethodExecutionsThreadResult> timingResultForCurrentThread = new ThreadLocal<MethodExecutionsThreadResult>();

    @Override
    public void traceStarted(Method method) {
        timingResultForCurrentThread.set(new MethodExecutionsThreadResult(method.getDeclaringClass().getSimpleName()));
    }

    @Override
    public void traceStopped(Method method) {
        MethodExecutionsThreadResult timingResult = timingResultForCurrentThread.get();
        timingResultForCurrentThread.remove();
        System.out.println(methodExecutionFormatter.format(timingResult.getMethodExecutionId(), timingResult.getFinishedMethodExecutions()));
    }

    @Override
    public void methodEntered(Method method) {
        timingResultForCurrentThread.get().pushMethodExecution(method);
    }

    @Override
    public void methodExited(Method method) {
        timingResultForCurrentThread.get().popMethodExecution();
    }
}
