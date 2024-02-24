package com.harald.emicalculatorbe.utils;

public class MathUtils {

    public static double roundTwoDecimals(double value) {
        return ((double) Math.round(value * 100) / 100);
    }

}
