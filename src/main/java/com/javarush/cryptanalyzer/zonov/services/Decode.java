package com.javarush.cryptanalyzer.zonov.services;

import com.javarush.cryptanalyzer.zonov.constants.CryptoAlphabet;
import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.exception.ApplicationException;
import com.javarush.cryptanalyzer.zonov.repository.ResultCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Decode implements Function {

    @Override
    public Result execute(String[] parameters) {
    try {
        decode(Integer.parseInt(parameters[1]), parameters[2], parameters[3]);
    } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("Операция расшифровки методом Цезаря завершилась с ошибкой", e));
    }
        return new Result(ResultCode.OK);
    }

       private void decode(int key, String inputFileName, String outputFileName) throws IOException {
           try (FileReader reader = new FileReader(inputFileName);
                BufferedReader buffer = new BufferedReader(reader);
                FileWriter writer = new FileWriter(outputFileName)) {

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
                   StringBuilder decoded = new StringBuilder();
                   for(char c : charArray) {
                       decoded.append(c);
                   }
                   writer.write(decoded + "\n");
               }
           }
    }
}
