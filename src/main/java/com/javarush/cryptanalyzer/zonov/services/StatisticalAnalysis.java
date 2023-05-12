package com.javarush.cryptanalyzer.zonov.services;

import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.exception.ApplicationException;
import com.javarush.cryptanalyzer.zonov.repository.ResultCode;

import java.io.*;
import java.util.*;

import static com.javarush.cryptanalyzer.zonov.constants.ConsoleViewConstants.NEW_LINE;

public class StatisticalAnalysis implements Function{

    private static String fileForEdit;
    @Override
    public Result execute(String[] parameters) {
        try {
            statisticalAnalysisDecode(parameters[2], parameters[3], parameters[5]);
            fileForEdit = parameters[3];
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("Операция расшифровки методом статистического анализа завершилась с ошибкой", e));
        }
        return new Result(ResultCode.SYMBOL_CHANGE);
    }
    public void executeSymbolChange(String[] symbols) {
        try {
            symbolChange(symbols, fileForEdit);
        } catch (Exception e) {
            new Result(ResultCode.ERROR, new ApplicationException("Операция замены символов завершилась с ошибкой", e));
        }
    }

    private void  statisticalAnalysisDecode(String inputFileName, String outputFileName, String dictionaryFileName) throws IOException {

        fileToLowerCase(dictionaryFileName);

        Map<Character, Integer> dictionarySortedMap = mapSetter(dictionaryFileName);
        Map<Character, Integer> fileSortedMap = mapSetter(inputFileName);

        List<Character> dictionaryCharSortedList = new ArrayList<>(dictionarySortedMap.keySet());
        List<Character> fileCharSortedList = new ArrayList<>(fileSortedMap.keySet());

        decode(inputFileName, outputFileName, dictionaryCharSortedList, fileCharSortedList);

    }

    private void symbolChange(String[] symbols, String fileName) throws IOException{
        StringBuilder changingSymbols = new StringBuilder();
        try (FileReader reader = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(reader)) {

            while(buffer.ready()) {
                char[] charArray = buffer.readLine().toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    if(String.valueOf(charArray[i]).equals(symbols[0])) {
                        String symbol = symbols[1];
                        charArray[i] = symbol.charAt(0);
                    }
                }
                for(char c : charArray) {
                    changingSymbols.append(c);
                }
                changingSymbols.append(NEW_LINE);
            }
        }
        try(FileWriter writer = new FileWriter(fileName)) {
            writer.write(String.valueOf(changingSymbols));
        }
    }

    private void decode(String inputFileName, String outputFileName, List<Character> dictionaryCharList, List<Character> fileCharList) throws IOException {

        try(FileReader reader = new FileReader(inputFileName);
            BufferedReader buffer = new BufferedReader(reader);
            FileWriter writer = new FileWriter(outputFileName)) {

            while (buffer.ready()) {
                char[] charArray = buffer.readLine().toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    if(fileCharList.contains(charArray[i])) {
                        int inputCharIndex = fileCharList.indexOf(charArray[i]);
                        if (inputCharIndex < dictionaryCharList.size()) {
                            charArray[i] = dictionaryCharList.get(inputCharIndex);
                        } else {
                            charArray[i] = dictionaryCharList.get(dictionaryCharList.size() - 1);
                        }
                    }
                }
                StringBuilder decoded = new StringBuilder();
                for(char c : charArray) {
                    decoded.append(c);
                }
                writer.write(decoded + NEW_LINE);
            }
        }
    }

    private Map<Character, Integer> mapSetter (String fileName) throws IOException {
        Map<Character, Integer> fileCharCount = new HashMap<>();

        try (FileReader reader = new FileReader(fileName);
             BufferedReader buffer = new BufferedReader(reader)) {

            while (buffer.ready()) {
                char[] charArray = buffer.readLine().toCharArray();
                for (char c : charArray) {
                    if (!(fileCharCount.containsKey(c))) {
                        fileCharCount.put(c, 1);
                    } else {
                        int counter = fileCharCount.get(c);
                        counter++;
                        fileCharCount.put(c, counter);
                    }
                }
            }
        }

        return sortByValues(fileCharCount);
    }


    private static Map<Character, Integer> sortByValues(Map<Character, Integer> map) {
    Comparator<Character> comparator = new ValueComparator<>(map);
    Map<Character, Integer> sortedMap = new TreeMap<>(comparator);
    sortedMap.putAll(map);

    return  sortedMap;
    }

    private void fileToLowerCase(String fileName) throws IOException {
        StringBuilder lowerCased = new StringBuilder();
        try(FileReader reader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(reader)) {

            while (buffer.ready()) {
                String str = buffer.readLine().toLowerCase();
                char[] chars = str.toCharArray();
                for (char c : chars) {
                    lowerCased.append(c);
                }
                lowerCased.append(NEW_LINE);
            }
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(String.valueOf(lowerCased));
            }
        }
    }

}