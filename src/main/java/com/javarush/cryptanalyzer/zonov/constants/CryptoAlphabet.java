package com.javarush.cryptanalyzer.zonov.constants;

public class CryptoAlphabet {
    private static final String lettersUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String lettersLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String numbers = "0123456789";
    private static final String symbols = ".,\":-!? ";
    private static final String ALPHABET = lettersUpperCase + numbers + lettersLowerCase + symbols;

    public static String getALPHABET() {
        return ALPHABET;
    }
}
