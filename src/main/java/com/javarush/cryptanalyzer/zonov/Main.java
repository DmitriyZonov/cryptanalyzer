package com.javarush.cryptanalyzer.zonov;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
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
                            Encryptor.setRandomKey();
                            Encryptor.encrypt();
                        } else if (answer == 1) {
                            rightChoiceOfEncryptionMode = true;
                            System.out.println(TextExpressions.GET_INPUT_FILE_PATH);
                            Encryptor.setInputFileName(console.next());
                            System.out.println(TextExpressions.GET_OUTPUT_FILE_PATH);
                            Encryptor.setOutputFileName(console.next());
                            Encryptor.setKey();
                            Encryptor.encrypt();
                        } else {
                            System.out.println(TextExpressions.WRONG_ENCRYPTOR_CHOICE);
                        }
                    }
                } else if (mainAnswer == 1) {
                    rightMainChoice = true;
                    System.out.println(TextExpressions.CHOICE_DECRYPTION);
                    boolean rightChoiceDecryption = false;
                    while(!(rightChoiceDecryption)) {
                        int answer = console.nextInt();
                        if (answer == 0) {
                            rightChoiceDecryption = true;
                            Encryptor.setRandomKey();
                            Encryptor.encrypt();
                            Decryptor.decrypt(Encryptor.getKey());
                        } else if (answer == 1) {
                            rightChoiceDecryption = true;
                            System.out.println(TextExpressions.GET_INPUT_FILE_PATH);
                            Decryptor.setInputFileName(console.next());
                            System.out.println(TextExpressions.GET_OUTPUT_FILE_PATH);
                            Decryptor.setOutputFileName(console.next());
                            System.out.println(TextExpressions.SET_KEY_FOR_DECRYTION);
                            Decryptor.decrypt(console.nextInt());
                        } else {
                            System.out.println(TextExpressions.WRONG_DECRYPTOR_CHOICE);
                        }
                    }

                } else {
                    System.out.println(TextExpressions.WRONG_MAIN_CHOICE);
                }
            }
        }
    }
}