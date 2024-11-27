package com.tistory.cnux9.kiosk_challenge.lv2.util;

public class Format {
    public static String blue(String s) {
        return "\u001B[34m" + s + "\u001B[0m";
    }
    public static String magenta(String s) {
        return "\u001B[35m" + s + "\u001B[0m";
    }
    public static String red(String s) {
        return "\u001B[36m" + s + "\u001B[0m";
    }
    public static String yellow(String s) {
        return "\u001B[37m" + s + "\u001B[0m";
    }
}
