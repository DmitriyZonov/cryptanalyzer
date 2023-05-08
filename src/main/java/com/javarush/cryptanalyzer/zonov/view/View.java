package com.javarush.cryptanalyzer.zonov.view;

import com.javarush.cryptanalyzer.zonov.entity.Result;

public interface View {
    String[] getParameters();

    void printResult(Result result);

}
