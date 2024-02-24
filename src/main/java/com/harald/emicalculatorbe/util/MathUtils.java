package com.harald.emicalculatorbe.util;

public class MathUtils {

    public static double roundTwoDecimals(final double value) {
        return ((double) Math.round(value * 100) / 100);
    }

}
