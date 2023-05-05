package com.javarush.cryptanalyzer.zonov;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(TextExpressions.GREETING);
        Scanner console = new Scanner(System.in);
        System.out.println(TextExpressions.CHOICE);
        boolean rightChoice = false;
        while(!(rightChoice)) {
            int answer = console.nextInt();
            if (answer == 0) {
                rightChoice = true;
                int key = 0;
                boolean rightKey = false;
                System.out.println(TextExpressions.GET_KEY);

                while(!(rightKey)) {
                    key = console.nextInt();
                    if (key >= 1 && key <= 84) {
                        rightKey = true;
                    } else {
                        System.out.println(TextExpressions.WRONG_KEY);
                    }
                }

                try (FileReader reader = new FileReader("input.txt");
                     BufferedReader buffer = new BufferedReader(reader);
                     FileWriter writer = new FileWriter("encoded.txt")) {

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
                    }
                }
            } else if (answer == 1) {
                rightChoice = true;
            } else {
                System.out.println(TextExpressions.WRONG_CHOICE);
            }

        }


    }
}