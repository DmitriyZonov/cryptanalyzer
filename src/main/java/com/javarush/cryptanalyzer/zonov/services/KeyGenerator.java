package com.javarush.cryptanalyzer.zonov.services;

import java.util.Random;

public class KeyGenerator {

    public int keyGenerate() {
        return new Random().nextInt(84) + 1;
    }
}
