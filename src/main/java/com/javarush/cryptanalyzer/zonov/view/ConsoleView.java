package com.javarush.cryptanalyzer.zonov.view;

import com.javarush.cryptanalyzer.zonov.constants.ConsoleViewConstants;
import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.services.KeyChecker;
import com.javarush.cryptanalyzer.zonov.services.KeyGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static com.javarush.cryptanalyzer.zonov.constants.ApplicationCompletionConstants.*;
import static com.javarush.cryptanalyzer.zonov.constants.ConsoleViewConstants.*;

public class ConsoleView implements View {

    @Override
    public String[] getParameters() {
        String[] parameters = new String[8];
        Scanner console = new Scanner(System.in);
        System.out.println(GREETING);
            System.out.println(CHOOSE_FUNCTION);
            parameters[0] = console.nextLine();
            switch (parameters[0]) {
                case "1" -> System.out.println(ENCODING_CHOICE);
                case "2" -> System.out.println(DECODING_CHOICE);
                case "3" -> System.out.println(BRUTE_FORCE_CHOICE);
                case "4" -> System.out.println(STATISTICAL_ANALYSIS_CHOICE);
                default -> System.out.println(UNSUPPORTED_FUNCTION_CHOICE);
            }
            if (parameters[0].equals("1") || parameters[0].equals("2")) {
                while (!(KeyChecker.isKeyChecker())) {
                    System.out.println(CHOOSE_KEY_PRESENCE);
                    parameters[1] = console.nextLine();
                    if (parameters[0].equals("1") && parameters[1].equals("")) {
                        KeyChecker.setKeyChecker(true);
                        parameters[1] = String.valueOf(new KeyGenerator().keyGenerate());
                        System.out.println(KEY_GENERATE + parameters[1] + NEW_LINE);
                    } else if (parameters[0].equals("2") && parameters[1].equals("")) {
                        KeyChecker.setKeyChecker(true);
                        System.out.println(HAVENT_KEY_DECODE);
                        parameters[0] = "3";
                    } else {
                        if (KeyChecker.checkKey(parameters[1])) {
                            KeyChecker.setKeyChecker(true);
                        } else {
                            System.out.println(WRONG_KEY);
                        }
                    }
                }
            }
            if (parameters[0].equals("1")) {
                System.out.println(INPUT_FILE_PATH_PRESENCE_ENCODE);
                parameters[2] = console.nextLine();
                System.out.println(ENCODING_FOR_STATISTICAL_ANALYSIS);
                parameters[4] = console.nextLine();
                if (parameters[4].equals("")) {
                    parameters[4] = "0";
                }
                if (parameters[2].equals("")) {
                    parameters[2] = INPUT;
                    parameters[3] = ENCODED;
                    System.out.println();
                } else {
                    System.out.println(OUTPUT_FILE_PATH_PRESENCE_ENCODE);
                    parameters[3] = console.nextLine();
                }
            } else if (parameters[0].equals("2") || parameters[0].equals("3")) {
                System.out.println(FILE_PATH_PRESENCE_DECODE);
                parameters[2] = console.nextLine();
                if (parameters[2].equals("")) {
                    parameters[2] = ENCODED;
                    parameters[3] = OUTPUT;
                } else {
                    parameters[3] = console.nextLine();
                }
            } else if (parameters[0].equals("4")) {
                System.out.println(FILE_PATH_PRESENCE_STATISTICAL_ANALYSIS_DECODE);
                parameters[2] = console.nextLine();
                if (parameters[2].equals("")) {
                    parameters[2] = ENCODED;
                    parameters[3] = OUTPUT;
                    parameters[5] = DICTIONARY;
                } else {
                    parameters[3] = console.nextLine();
                    parameters[5] = console.nextLine();
                }
            }
        return parameters;
    }
    @Override
    public String[] getSymbols() throws IOException {
        String[] symbols = new String[2];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(HAVE_SYMBOLS_FOR_CHANGE);
        symbols[0] = bufferedReader.readLine();
        if (!symbols[0].equals("")) {
            System.out.println(SET_CHANGING_SYMBOL);
            symbols[1] = bufferedReader.readLine();
        }
        return symbols;
    }

    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }
}
