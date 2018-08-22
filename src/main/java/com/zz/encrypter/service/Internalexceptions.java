package com.zz.encrypter.service;

class InputFormatException extends Exception {

    public InputFormatException() {
        super();
    }

    public InputFormatException(String message) {
        super(message);
    }

    public InputFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputFormatException(Throwable cause) {
        super(cause);
    }

    protected InputFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

class PasswordException extends Exception{

    public PasswordException() {
        super();
    }

    public PasswordException(String message) {
        super(message);
    }

    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordException(Throwable cause) {
        super(cause);
    }

    protected PasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

/**
 * 用于表示校验和不正确的情况
 */
class CheckSumException extends Exception{

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