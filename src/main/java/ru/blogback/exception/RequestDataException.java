package ru.blogback.exception;

public class RequestDataException extends RuntimeException {
    public RequestDataException() {
        super();
    }

    public RequestDataException(String message) {
        super(message);
    }
}
