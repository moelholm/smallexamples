package com.moelholm.profiling.timingsupport;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MethodExecutionsThreadResult {

    private final String methodExecutionId;

    private final Stack<MethodExecution> methodExecutionsStack = new Stack<MethodExecution>();

    private final List<MethodExecution> methodExecutionsList = new LinkedList<MethodExecution>();

    public MethodExecutionsThreadResult(String methodExecutionId) {
        this.methodExecutionId = methodExecutionId;
    }

    public String getMethodExecutionId() {
        return methodExecutionId;
    }

    public void popMethodExecution() {
        MethodExecution methodExecution = methodExecutionsStack.pop();
        methodExecution.complete();
    }

    public void pushMethodExecution(Method method) {
        MethodExecution methodExecution = new MethodExecution(methodExecutionsStack.size(), method);
        methodExecutionsStack.push(methodExecution);
        methodExecutionsList.add(methodExecution);
    }

    public List<MethodExecution> getFinishedMethodExecutions() {
        return methodExecutionsList;
    }
}