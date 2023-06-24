package ru.itis.javalab.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.javalab.dto.ExceptionDto;
import ru.itis.javalab.exceptions.NotFoundException;
import ru.itis.javalab.exceptions.SignUpException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFound(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionDto.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<ExceptionDto> handleSignUpException(SignUpException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionDto.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.CONFLICT.value())
                        .build());
    }
}

