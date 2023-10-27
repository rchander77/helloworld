package com.example.helloworld;

import org.springframework.http.HttpStatus;
public class NotFoundException extends RuntimeException {
    private final int code = HttpStatus.NOT_FOUND.value();
    private final String searchAttribute;

    public NotFoundException(String msg, String searchAttribute, Throwable throwable) {
        super(msg, throwable);
        this.searchAttribute = searchAttribute;
    }

    public int getCode() {
        return code;
    }

    public String getSearchAttribute() {
        return searchAttribute;
    }
}