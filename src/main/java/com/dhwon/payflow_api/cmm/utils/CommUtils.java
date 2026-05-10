package com.dhwon.payflow_api.cmm.utils;

public final class CommUtils {
    private CommUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String truncateStr(String str, int maxLength) {
        if (str == null) return null;
        return str.length() > maxLength ? str.substring(0, maxLength) + "...(truncated)" : str;
    }
}