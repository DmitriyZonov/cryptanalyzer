package com.javarush.cryptanalyzer.zonov.entity;

import com.javarush.cryptanalyzer.zonov.exception.ApplicationException;
import com.javarush.cryptanalyzer.zonov.repository.ResultCode;

public class Result {

    private ResultCode resultCode;
    private ApplicationException applicationException;
    public Result (ResultCode resultCode) {
        this.resultCode = resultCode;
    }
    public Result (ResultCode resultCode, ApplicationException applicationException) {
        this.resultCode = resultCode;
        this.applicationException = applicationException;
    }
    public ResultCode getResultCode() {
        return resultCode;
    }
    public ApplicationException getApplicationException() {
        return applicationException;
    }
}
