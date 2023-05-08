package com.javarush.cryptanalyzer.zonov.services;

public class KeyChecker {
    private static boolean keyChecker = false;

    public static boolean isKeyChecker() {
        return keyChecker;
    }

    public static void setKeyChecker(boolean keyChecker) {
        KeyChecker.keyChecker = keyChecker;
    }

    public static boolean checkKey(String key) {
        return Integer.parseInt(key) >= 1 && Integer.parseInt(key) <= 83 ;
    }
}
