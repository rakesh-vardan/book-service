package com.epam.jpop.bookservice.exception;

public class BookIdMismatchException extends Exception {

    public BookIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookIdMismatchException(){

    }
}
