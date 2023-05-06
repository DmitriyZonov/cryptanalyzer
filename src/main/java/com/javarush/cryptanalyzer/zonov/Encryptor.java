package com.javarush.cryptanalyzer.zonov;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Encryptor {
    private static int key;
    private static String inputFileName = "input.txt";
    private static String outputFileName = "encoded.txt";

    public static int getKey() {
        return key;
    }

    public static void setKey() {
        try(Scanner console = new Scanner(System.in)) {
            System.out.println(TextExpressions.GET_KEY);
            int key;
            boolean rightKey = false;
            while (!(rightKey)) {
                key = console.nextInt();
                if (key >= 1 && key <= 84) {
                    rightKey = true;
                    Encryptor.key = key;
                } else {
                    System.out.println(TextExpressions.WRONG_KEY);
                }
            }
        }
    }

    public static void setRandomKey() {
        Random random = new Random();
        key = random.nextInt(85) + 1;
        System.out.println(TextExpressions.SET_RANDOM_KEY);
    }

    public static void setInputFileName(String inputFileName) {
        Encryptor.inputFileName = inputFileName;
    }

    public static void setOutputFileName(String outputFileName) {
        Encryptor.outputFileName = outputFileName;
    }
    public static void encrypt() throws IOException {

        try (FileReader reader = new FileReader(Encryptor.inputFileName);
             BufferedReader buffer = new BufferedReader(reader);
             FileWriter writer = new FileWriter(Encryptor.outputFileName)) {

            char[] alphabet = CryptoAlphabet.getALPHABET().toCharArray();

            while (buffer.ready()) {
                String line = buffer.readLine();
                char[] charArray = line.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    for (int j = 0; j < alphabet.length; j++) {
                        int temp = 0;
                        if(charArray[i] == alphabet[j]) {
                            if ((j + key) > alphabet.length - 1) {
                                temp = key - (alphabet.length - j);
                            } else {
                                temp = j + key;
                            }
                            charArray[i] = alphabet[temp];
                            break;
                        }
                    }
                }
                StringBuilder encoded = new StringBuilder();
                for(char c : charArray) {
                    encoded.append(c);
                }
                writer.write(encoded.toString());
                writer.write("\n");
            }
        }
    }
}
