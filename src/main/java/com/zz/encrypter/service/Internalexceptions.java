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