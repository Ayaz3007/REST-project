package ru.itis.javalab.validation.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.javalab.dto.ExceptionDto;
import ru.itis.javalab.exceptions.NotFoundException;
import ru.itis.javalab.validation.responses.ValidationErrorDto;
import ru.itis.javalab.validation.responses.ValidationErrorsDto;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleNotFound(MethodArgumentNotValidException e) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();

            String fieldName = null;
            String objectName = error.getObjectName();

            if (error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            }

            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .field(fieldName)
                    .object(objectName)
                    .build();
            errors.add(errorDto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(ValidationErrorsDto.builder()
                .errors(errors)
                .build());
    }
}
