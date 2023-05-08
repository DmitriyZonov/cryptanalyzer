package com.javarush.cryptanalyzer.zonov.services;

import com.javarush.cryptanalyzer.zonov.entity.Result;

public interface Function {
    Result execute(String[] parameters);
}
