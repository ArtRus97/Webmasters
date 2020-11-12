package com.example.webmasters;

public class Utils {
    static public Number minmax(Number value, Number minimum, Number maximum) {
        return Math.max(minimum.doubleValue(), Math.min(value.doubleValue(), maximum.doubleValue()));
    }
}
