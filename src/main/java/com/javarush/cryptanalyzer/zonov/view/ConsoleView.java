package com.javarush.cryptanalyzer.zonov.view;

import com.javarush.cryptanalyzer.zonov.constants.ConsoleViewConstants;
import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.services.KeyChecker;
import com.javarush.cryptanalyzer.zonov.services.KeyGenerator;

import java.util.Scanner;

import static com.javarush.cryptanalyzer.zonov.constants.ApplicationCompletionConstants.EXCEPTION;
import static com.javarush.cryptanalyzer.zonov.constants.ApplicationCompletionConstants.SUCCESS;

public class ConsoleView implements View {
    @Override
    public String[] getParameters() {
        String[] parameters = new String[6];
        System.out.println(ConsoleViewConstants.GREETING);
        try (Scanner console = new Scanner(System.in)) {
            System.out.println(ConsoleViewConstants.CHOOSE_FUNCTION);
            parameters[0] = console.nextLine();
            switch (parameters[0]) {
                case "1" -> System.out.println(ConsoleViewConstants.ENCODING_CHOICE);
                case "2" -> System.out.println(ConsoleViewConstants.DECODING_CHOICE);
                case "3" -> System.out.println(ConsoleViewConstants.BRUTE_FORCE_CHOICE);
                case "4" -> System.out.println(ConsoleViewConstants.STATISTICAL_ANALYSIS_CHOICE);
                default -> System.out.println(ConsoleViewConstants.UNSUPPORTED_FUNCTION_CHOICE);
            }
            if (parameters[0].equals("1") || parameters[0].equals("2")) {
                while (!(KeyChecker.isKeyChecker())) {
                    System.out.println(ConsoleViewConstants.CHOOSE_KEY_PRESENCE);
                    parameters[1] = console.nextLine();
                    if (parameters[0].equals("1") && parameters[1].equals("")) {
                        KeyChecker.setKeyChecker(true);
                        parameters[1] = String.valueOf(new KeyGenerator().keyGenerate());
                        System.out.println(ConsoleViewConstants.KEY_GENERATE + parameters[1] + ConsoleViewConstants.NEW_LINE);
                    } else if (parameters[0].equals("2") && parameters[1].equals("")) {
                        KeyChecker.setKeyChecker(true);
                        System.out.println(ConsoleViewConstants.HAVENT_KEY_DECODE);
                        parameters[0] = "3";
                    } else {
                        if (KeyChecker.checkKey(parameters[1])) {
                            KeyChecker.setKeyChecker(true);
                        } else {
                            System.out.println(ConsoleViewConstants.WRONG_KEY);
                        }
                    }
                }
            }
            if (parameters[0].equals("1")) {
                System.out.println(ConsoleViewConstants.INPUT_FILE_PATH_PRESENCE_ENCODE);
                parameters[2] = console.nextLine();
                System.out.println(ConsoleViewConstants.ENCODING_FOR_STATISTICAL_ANALYSIS);
                parameters[4] = console.nextLine();
                if (parameters[4].equals("")) {
                    parameters[4] = "0";
                }
                if (parameters[2].equals("")) {
                    parameters[2] = ConsoleViewConstants.INPUT;
                    parameters[3] = ConsoleViewConstants.ENCODED;
                    System.out.println();
                } else {
                    System.out.println(ConsoleViewConstants.OUTPUT_FILE_PATH_PRESENCE_ENCODE);
                    parameters[3] = console.next();
                }
            } else if (parameters[0].equals("2") || parameters[0].equals("3")) {
                System.out.println(ConsoleViewConstants.FILE_PATH_PRESENCE_DECODE);
                parameters[2] = console.nextLine();
                if (parameters[2].equals("")) {
                    parameters[2] = ConsoleViewConstants.ENCODED;
                    parameters[3] = ConsoleViewConstants.OUTPUT;
                } else {
                    parameters[3] = console.nextLine();
                }
            } else if (parameters[0].equals("4")) {
                System.out.println(ConsoleViewConstants.FILE_PATH_PRESENCE_STATISTICAL_ANALYSIS_DECODE);
                parameters[2] = console.nextLine();
                if (parameters[2].equals("")) {
                    parameters[2] = ConsoleViewConstants.ENCODED;
                    parameters[3] = ConsoleViewConstants.OUTPUT;
                    parameters[5] = ConsoleViewConstants.DICTIONARY;
                } else {
                    parameters[3] = console.nextLine();
                    parameters[5] = console.nextLine();
                }
            }
        }
        return parameters;
    }

    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }
}
