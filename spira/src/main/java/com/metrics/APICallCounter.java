package com.metrics;

public class APICallCounter {
    public static int get() {
        return _apiCalls;
    }

    public static void addCall() {
        _apiCalls += 1;
    }

    private static int _apiCalls = 0;
 }
