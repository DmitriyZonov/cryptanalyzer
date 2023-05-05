package com.javarush.cryptanalyzer.zonov;

public class TextExpressions {
    public static final String GREETING = "Добро пожаловать в программу по шифрованию текста \"Цезарь\"" + "\n";
    public static final String MAIN_CHOICE = "Выберете режим работы:\n" + "Нажмите 0 для шифрования файла;\n" + "Нажмите 1 для расшифровки файла;\n";
    public static final String WRONG_MAIN_CHOICE = "Выбрано неверное число, пожалуйста, введите следующие числа: \n" + "\n" + MAIN_CHOICE;
    public static final String CHOICE_ENCRYPTION = "Нажмите 0 для выбора тестового файла для шифрования;\n" +
                                        "Нажмите 1 для указания собственных путей файлов для шифрования и получения результата;\n";
    public static final String WRONG_CHOICE = "Выбрано неверное число, пожалуйста, введите следующие числа: \n" + "\n" + CHOICE_ENCRYPTION;
    public static final String GET_KEY = "Введите ключ шифрования. Это должно быть целое число от 1 до 84\n";
    public static final String WRONG_KEY = "Ключ шифрования неверен. Введите целое число от 1 до 84\n";
    public static final String GET_INPUT_FILE_PATH = "Введите путь для файла с текстом для шифрования\n";
    public static final String GET_OUTPUT_FILE_PATH = "Введите путь для файла для выгрузки результата шифрования\n";
}
