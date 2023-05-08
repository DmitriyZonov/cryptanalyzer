package com.javarush.cryptanalyzer.zonov.services;

import com.javarush.cryptanalyzer.zonov.entity.Result;

public class UnsupportedFunction implements Function {

    @Override
    public Result execute(String[] parameters) {
        return null;
    }
}
