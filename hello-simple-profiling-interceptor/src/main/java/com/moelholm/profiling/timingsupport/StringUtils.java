package com.moelholm.profiling.timingsupport;

public class StringUtils {

    public static String getIndent(int indentValue) {
        return repeat("    ", indentValue);
    }

    public static String repeat(String what, int howManyTimes) {
        StringBuilder rep = new StringBuilder();
        for (int i = 0; i < howManyTimes; i++) {
            rep.append(what);
        }
        return rep.toString();
    }
}