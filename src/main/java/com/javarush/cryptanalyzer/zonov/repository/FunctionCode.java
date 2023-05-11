package com.javarush.cryptanalyzer.zonov.repository;

import com.javarush.cryptanalyzer.zonov.services.*;

public enum FunctionCode {
    ENCODE(new Encode()), DECODE(new Decode()), BRUTE_FORCE(new BruteForce()), STATISTICAL_ANALYSIS(new StatisticalAnalysis()), UNSUPPORTED_FUNCTION(new UnsupportedFunction());

    private Function function;
    FunctionCode(Function function) {
        this.function = function;
    }
    public Function getFunction() {
        return function;
    }
}
