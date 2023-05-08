package com.javarush.cryptanalyzer.zonov.services;

import com.javarush.cryptanalyzer.zonov.entity.Result;
import com.javarush.cryptanalyzer.zonov.exception.ApplicationException;
import com.javarush.cryptanalyzer.zonov.repository.ResultCode;

public class BruteForce implements Function {
    @Override
    public Result execute(String[] parameters) {
        try {
            //TODO add BruteForce execute method
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("Операция расшифровки методом \"Брут форс\" завершилась с ошибкой", e));
        }
        return new Result(ResultCode.OK);
    }
}
