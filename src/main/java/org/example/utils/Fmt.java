package org.example.utils;

public class Fmt {
    public static String val(double x) {
        if (Double.isInfinite(x)) return "Infinity";
        if (x > 1e15) return "Infinity";
        if (x < -1e15) return "-Infinity";
        return String.format("%.1f", x);
    }
}
