package com.javarush.cryptanalyzer.zonov.constants;

public class ConsoleViewConstants {
    public static final String NEW_LINE = "\n";
    public static final String INPUT = "input.txt";
    public static final String OUTPUT = "output.txt";
    public static final String ENCODED = "encoded.txt";
    public static final String DICTIONARY = "statisticDictionary.txt";
    public static final String GREETING = "Добро пожаловать в программу по шифрованию текста \"Цезарь и Брут\"" + NEW_LINE;
    public static final String CHOOSE_FUNCTION = """
            Выберите режим работы:
            Нажмите 1 для шифрования файла;
            Нажмите 2 для расшифровки файла;
            Нажмите 3 для расшифровки методом "Брут Форс"
            Нажмите 4 для расшифровки методом статистического анализа
            """;
    public static final String ENCODING_CHOICE = "Выбран режим шифрования методом Цезаря" + NEW_LINE;
    public static final String DECODING_CHOICE = "Выбран режим расшифровки" + NEW_LINE;
    public static final String BRUTE_FORCE_CHOICE = "Выбран режим расшифровки методом \"Брут Форс\"" + NEW_LINE;
    public static final String STATISTICAL_ANALYSIS_CHOICE = "Выбран режим расшифровки методом статистического анализа";
    public static final String UNSUPPORTED_FUNCTION_CHOICE = "Выбран неподдерживаемый режим работы";
    public static final String CHOOSE_KEY_PRESENCE = "Укажите ключ шифрования (целое число в диапазоне от 1 до 84)\nИли нажмите ENTER, если он Вам неизвестен" + NEW_LINE;
    public static final String KEY_GENERATE = "Ключ сгенерирован, шифровка пройдет с ключом: ";
    public static final String HAVENT_KEY_DECODE = "Метод дешифровки будет изменён на \"Брут Форс\"" + NEW_LINE;
    public static final String WRONG_KEY = "Ключ шифрования неверен" + NEW_LINE;
    public static final String ENCODING_FOR_STATISTICAL_ANALYSIS = "Будет ли этот файл использоваться для расшифровки методом статистического анализа?"+ NEW_LINE +
            "Нажмите 1, если да, или нажмите ENTER, если нет";
    public static final String INPUT_FILE_PATH_PRESENCE_ENCODE = "Введите путь нахождения исходного текстового файла" + NEW_LINE +
            "Или нажмите ENTER для использования тестовых файлов" + NEW_LINE;
    public static final String OUTPUT_FILE_PATH_PRESENCE_ENCODE = "Введите путь нахождения файла для выгрузки результата шифровки" + NEW_LINE;
    public static final String FILE_PATH_PRESENCE_DECODE = "Введите пути нахождения исходного зашифрованного файла и файла для выгрузки результата расшифровки" + NEW_LINE +
            "Или нажмите ENTER для использования тестовых файлов" + NEW_LINE;
    public static final String FILE_PATH_PRESENCE_STATISTICAL_ANALYSIS_DECODE = "Введите пути нахождения следующих файлов: " + NEW_LINE +
            "- зашифрованного файла" + NEW_LINE +
            "- файла для выгрузки результата расшифровки" + NEW_LINE +
            "- файла-словаря того же автора и стиля" + NEW_LINE +
            "Или нажмите ENTER для использования тестовых файлов" + NEW_LINE;
}
