package com.javarush.cryptanalyzer.zonov.controller;

import com.javarush.cryptanalyzer.zonov.view.View;

public class MainController {
    private View view;
    public MainController(View view) {
        this.view = view;
    }
    public View getView() {
        return view;
    }
}
