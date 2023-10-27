package com.example.helloworld;

public class ApplicationException extends Exception {

    public ApplicationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}