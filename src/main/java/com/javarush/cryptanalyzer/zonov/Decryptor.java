package com.javarush.cryptanalyzer.zonov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Decryptor {
    private static int key;
    private static String inputFileName = "encoded.txt";
    private static String outputFileName = "output.txt";

    public static int getKey() {
        return key;
    }


    public static void setKey() {
        try(Scanner console = new Scanner(System.in)) {
            System.out.println(TextExpressions.SET_KEY_FOR_DECRYPTION);
            int key;
            boolean rightKey = false;
            while (!(rightKey)) {
                key = console.nextInt();
                if (key >= 1 && key <= 84) {
                    rightKey = true;
                    Decryptor.key = key;
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
        Decryptor.inputFileName = inputFileName;
    }

    public static void setOutputFileName(String outputFileName) {
        Decryptor.outputFileName = outputFileName;
    }
    public static void decrypt(int key) throws IOException {

        try (FileReader reader = new FileReader(Decryptor.inputFileName);
             BufferedReader buffer = new BufferedReader(reader);
             FileWriter writer = new FileWriter(Decryptor.outputFileName)) {

            char[] alphabet = CryptoAlphabet.getALPHABET().toCharArray();

            while (buffer.ready()) {
                String line = buffer.readLine();
                char[] charArray = line.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    for (int j = 0; j < alphabet.length; j++) {
                        int temp = 0;
                        if(charArray[i] == alphabet[j]) {
                            if ((j - key) < 0) {
                                temp = alphabet.length - (key - j);
                            } else {
                                temp = j - key;
                            }
                            charArray[i] = alphabet[temp];
                            break;
                        }
                    }
                }
                StringBuilder decoded = new StringBuilder();
                for(char c : charArray) {
                    decoded.append(c);
                }
                writer.write(decoded.toString());
            }
        }
    }
}
