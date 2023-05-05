package com.javarush.cryptanalyzer.zonov;

public class TextExpressions {
    public static final String GREETING = "Добро пожаловать в программу по шифрованию текста \"Цезарь\"" + "\n";
    public static final String CHOICE = "Нажмите 0 для выбора тестового файла для шифрования;\n" +
                                        "Нажмите 1 для указания собственного пути файла для шифрования;\n";
    public static final String WRONG_CHOICE = "Выбрано неверное число, пожалуйста, введите следующие числа: \n" + "\n" + CHOICE;
    public static final String GET_KEY = "Введите ключ шифрования. Это должно быть целое число от 1 до 84\n";
    public static final String WRONG_KEY = "Ключ шифрования неверен. Введите целое число от 1 до 84\n";
}
