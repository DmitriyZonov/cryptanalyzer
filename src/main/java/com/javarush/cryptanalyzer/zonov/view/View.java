package com.javarush.cryptanalyzer.zonov.view;

import com.javarush.cryptanalyzer.zonov.entity.Result;

import java.io.IOException;

public interface View {
    String[] getParameters();
    String[] getSymbols() throws IOException;

    void printResult(Result result);

}
