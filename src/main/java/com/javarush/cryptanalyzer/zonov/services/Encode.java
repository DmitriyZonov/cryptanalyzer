package com.javarush.cryptanalyzer.zonov.services;

import com.javarush.cryptanalyzer.zonov.constants.CryptoAlphabet;
import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.exception.ApplicationException;
import com.javarush.cryptanalyzer.zonov.repository.ResultCode;

import java.io.*;


public class Encode implements Function{
    @Override
    public Result execute(String[] parameters) {
        try {
            encode(Integer.parseInt(parameters[1]), parameters[2], parameters[3], Integer.parseInt(parameters[4]));
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("Операция шифрования завершилась с ошибкой", e));
        }
        return new Result(ResultCode.OK);
    }

    private void encode(int key, String inputFileName, String outputFileName, int statisticalAnalysisParameter) throws IOException {

        try (FileReader reader = new FileReader(inputFileName);
             BufferedReader buffer = new BufferedReader(reader);
             FileWriter writer = new FileWriter(outputFileName)) {

            char[] alphabet = CryptoAlphabet.getALPHABET().toCharArray();

            while (buffer.ready()) {
                char[] charArray;
                if (statisticalAnalysisParameter == 0) {
                    charArray = buffer.readLine().toCharArray();
                } else {
                    charArray = buffer.readLine().toLowerCase().toCharArray();
                }
                for (int i = 0; i < charArray.length; i++) {
                    for (int j = 0; j < alphabet.length; j++) {
                        int temp;
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
                writer.write(encoded + "\n");
            }
        }
    }
}
