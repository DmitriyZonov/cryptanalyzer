package com.javarush.cryptanalyzer.zonov;

import java.io.*;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchElementException {
        System.out.println(TextExpressions.GREETING);
        try (Scanner console = new Scanner(System.in)) {
            System.out.println(TextExpressions.MAIN_CHOICE);
            boolean rightMainChoice = false;
            while (!(rightMainChoice)) {
                int mainAnswer = console.nextInt();
                if (mainAnswer == 0) {
                    rightMainChoice = true;
                    System.out.println(TextExpressions.CHOICE_ENCRYPTION);
                    boolean rightChoiceOfEncryptionMode = false;
                    while (!(rightChoiceOfEncryptionMode)) {
                        int answer = console.nextInt();
                        if (answer == 0) {
                            rightChoiceOfEncryptionMode = true;
                            Encryption.setKey();
                            Encryption.encrypt();
                        } else if (answer == 1) {
                            rightChoiceOfEncryptionMode = true;
                            System.out.println(TextExpressions.GET_INPUT_FILE_PATH);
                            Encryption.setInputFileName(console.next());
                            System.out.println(TextExpressions.GET_OUTPUT_FILE_PATH);
                            Encryption.setOutputFileName(console.next());
                            Encryption.setKey();
                            Encryption.encrypt();
                            console.close();
                        } else {
                            System.out.println(TextExpressions.WRONG_CHOICE);
                        }
                    }
                } else if (mainAnswer == 1) {
                    rightMainChoice = true;

                } else {
                    System.out.println(TextExpressions.WRONG_MAIN_CHOICE);
                }
            }
        }
    }
}