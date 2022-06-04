package ru.blogback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.blogback.exception.ExceptionResponse;
import ru.blogback.exception.RequestDataException;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler( Exception.class )
    public ResponseEntity<?> handleInternalExceptions() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse("Произвошла непредвиденная ошибка")
                );
    }

    @ExceptionHandler(RequestDataException.class)
    public ResponseEntity<?> handleRequestDataException(RequestDataException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(e.getMessage())
                );
    }
}
