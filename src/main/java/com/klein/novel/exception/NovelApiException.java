package com.klein.novel.exception;

import org.springframework.http.HttpStatus;

public class NovelApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public NovelApiException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public NovelApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
