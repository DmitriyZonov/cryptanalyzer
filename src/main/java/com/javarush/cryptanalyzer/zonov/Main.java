package com.javarush.cryptanalyzer.zonov;

import com.javarush.cryptanalyzer.zonov.app.Application;
import com.javarush.cryptanalyzer.zonov.controller.MainController;
import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.view.ConsoleView;
import com.javarush.cryptanalyzer.zonov.view.GUIView;
import com.javarush.cryptanalyzer.zonov.view.View;

public class Main {
    public static void main(String[] args) {

        View view = new ConsoleView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);

        Result result = application.run();
        application.printResult(result);
    }
}