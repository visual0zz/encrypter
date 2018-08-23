package com.zz.encrypter.service;

/**
 * 用于表示校验和不正确的情况
 */
public class CheckSumException extends Exception{

    public CheckSumException() {
        super();
    }

    public CheckSumException(String message) {
        super(message);
    }

    public CheckSumException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckSumException(Throwable cause) {
        super(cause);
    }

    protected CheckSumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
