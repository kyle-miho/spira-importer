package com.util;

public class Validator {

    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }

        if (s.isEmpty()) {
            return true;
        }

        return false;
    }

    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    public static boolean isValidProjectId(int projectId) {
        if (projectId == 0) {
            return false;
        }

        return true;
    }

    public static boolean isNotValidProjectId(int projectId) {
        return !isValidProjectId(projectId);
    }
}
