package com.javarush.cryptanalyzer.zonov.app;

import com.javarush.cryptanalyzer.zonov.controller.MainController;
import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.repository.FunctionCode;
import com.javarush.cryptanalyzer.zonov.repository.ResultCode;
import com.javarush.cryptanalyzer.zonov.services.Function;
import com.javarush.cryptanalyzer.zonov.services.StatisticalAnalysis;

import java.io.IOException;

import static com.javarush.cryptanalyzer.zonov.constants.FunctionCodeConstants.*;
import static com.javarush.cryptanalyzer.zonov.repository.ResultCode.BRUTE_FORCE_COMPLETE;
import static com.javarush.cryptanalyzer.zonov.repository.ResultCode.SYMBOL_CHANGE;

public class Application {
    private final MainController mainController;

    public Application(MainController mainController) {
        this.mainController = mainController;
    }
    public Result run() {
        String[] parameters = mainController.getView().getParameters();
        String mode = parameters[0];
        Function function = getFunction(mode);
        return function.execute(parameters);
    }
    public Result runSymbolChange() throws IOException {
        while (true) {
            String[] symbols = mainController.getView().getSymbols();
            if (symbols[0].equals("")) {
                return new Result(ResultCode.OK);
            }
         new StatisticalAnalysis().executeSymbolChange(symbols);
        }
    }

    private Function getFunction(String mode) {
        return switch (mode) {
            case "1" -> FunctionCode.valueOf(ENCODE).getFunction();
            case "2" -> FunctionCode.valueOf(DECODE).getFunction();
            case "3" -> FunctionCode.valueOf(BRUTE_FORCE).getFunction();
            case "4" -> FunctionCode.valueOf(STATISTICAL_ANALYSIS).getFunction();
            default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };
    }

    public void printResult(Result result) {
        if (result.getResultCode() == SYMBOL_CHANGE) {
            try {
                Result resultOfSymbolChanging = runSymbolChange();
                mainController.getView().printResult(resultOfSymbolChanging);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (result.getResultCode() == BRUTE_FORCE_COMPLETE) {
            mainController.getView().printResult(result);
        } else {
            mainController.getView().printResult(result);
        }
    }
}
