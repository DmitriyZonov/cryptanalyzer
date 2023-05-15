package com.javarush.cryptanalyzer.zonov.services;

import com.javarush.cryptanalyzer.zonov.constants.CryptoAlphabet;
import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.exception.ApplicationException;
import com.javarush.cryptanalyzer.zonov.repository.ResultCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BruteForce implements Function {
    private static int key;
    @Override
    public Result execute(String[] parameters) {
        try {
           bruteForce(parameters[2], parameters[3]);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("Операция расшифровки методом \"Брут форс\" завершилась с ошибкой", e));
        }
        return new Result(ResultCode.BRUTE_FORCE_COMPLETE);
    }
    private void bruteForce(String inputFileName, String outputFileName) throws IOException {

        int alphabetLength = CryptoAlphabet.getALPHABET().toCharArray().length;
        int fileNumber = 0;
        for (int i = 1; i < alphabetLength; i++) {
            StringBuilder decoded = decode(i, inputFileName);

            if (decoded.indexOf(" я ") > 0 && decoded.indexOf(", а ") > 0 &&
                    decoded.indexOf(" как ") > 0 && decoded.indexOf(" в ") > 0) {
                try (FileWriter writer = new FileWriter(getNewFileName(outputFileName, fileNumber))) {
                    writer.write(String.valueOf(decoded));
                    key = i;
                    fileNumber++;
                }
            }
        }
    }
    private StringBuilder decode(int key, String inputFileName) throws IOException {

        StringBuilder decodedText = new StringBuilder();

        try (FileReader reader = new FileReader(inputFileName);
             BufferedReader buffer = new BufferedReader(reader)) {

            char[] alphabet = CryptoAlphabet.getALPHABET().toCharArray();

            while (buffer.ready()) {
                char[] charArray = buffer.readLine().toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    for (int j = 0; j < alphabet.length; j++) {
                        int temp;
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
                StringBuilder decodedLine = new StringBuilder();
                for(char c : charArray) {
                    decodedLine.append(c);
                }
                decodedText.append(decodedLine).append("\n");
            }
        }
        return decodedText;
    }

    public static int getKey() {
        return key;
    }

    private String getNewFileName(String oldFileName, int number) {
        if (number > 0) {
            int dotIndex = oldFileName.lastIndexOf(".");
            return oldFileName.substring(0, dotIndex) + number + oldFileName.substring(dotIndex);
        }
        return oldFileName;
    }

}
