package com.tistory.cnux9.kiosk_essential.lv5;

public class Format {
    public static String blue(String s) {
        return "\u001B[34m" + s + "\u001B[0m";
    }
    public static String magenta(String s) {
        return "\u001B[35m" + s + "\u001B[0m";
    }
}
