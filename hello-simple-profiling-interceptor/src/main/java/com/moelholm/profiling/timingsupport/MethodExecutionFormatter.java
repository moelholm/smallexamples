package com.moelholm.profiling.timingsupport;

import java.util.Collection;

public class MethodExecutionFormatter {

    private boolean printUnknownCodeBlocks;

    public static MethodExecutionFormatter create() {
        return new MethodExecutionFormatter();
    }

    public MethodExecutionFormatter withUnknownCodeBlocks() {
        this.printUnknownCodeBlocks = true;
        return this;
    }

    public String format(String methodExecutionName, Collection<MethodExecution> methodExecutions) {
        return format(methodExecutionName, methodExecutions.toArray(new MethodExecution[methodExecutions.size()]));
    }

    public String format(String methodExecutionName, MethodExecution... methodExecutions) {

        final int NUMBER_OF_SEPARATOR_CHARS = 84;

        StringBuilder result = new StringBuilder("\n");
        result.append(StringUtils.repeat("-", NUMBER_OF_SEPARATOR_CHARS)).append("\n");
        result.append(methodExecutionName).append("\n");
        result.append(StringUtils.repeat("-", NUMBER_OF_SEPARATOR_CHARS)).append("\n");

        long lastMethodExecutionStopTime = methodExecutions.length == 0 ? -1 : methodExecutions[0].getStartMilliseconds();
        for (MethodExecution methodExecution : methodExecutions) {

            String indent = StringUtils.getIndent(methodExecution.getCallLevel());
            String clazz = methodExecution.getMethod().getDeclaringClass().getSimpleName();
            String method = methodExecution.getMethod().getName();
            String prefix = String.format("%5s ms - ", methodExecution.getDuration());

            if (printUnknownCodeBlocks) {
                long durationSinceLastMethodExcecutionStopped = methodExecution.getStartMilliseconds() - lastMethodExecutionStopTime;
                if (durationSinceLastMethodExcecutionStopped > 0) {
                    String crazyPrefix = String.format("%5s ms - ", durationSinceLastMethodExcecutionStopped);
                    result.append(String.format("%s%s?%n", indent, crazyPrefix));
                }
            }

            result.append(String.format("%s%s%s.%s()%n", indent, prefix, clazz, method));
            lastMethodExecutionStopTime = methodExecution.getStopMilliseconds();
        }

        result.append(StringUtils.repeat("-", NUMBER_OF_SEPARATOR_CHARS)).append("\n");
        return result.toString();
    }
}
