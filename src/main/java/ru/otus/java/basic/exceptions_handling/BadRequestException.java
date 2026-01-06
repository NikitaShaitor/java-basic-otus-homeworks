package ru.otus.java.basic.exceptions_handling;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
