package org.example.supportservice.exceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ExceptionDetails> noSuchElement(NoSuchElementException exception) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .message(exception.getMessage())
                .httpStatus(status)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, status);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDetails> invalidId(MethodArgumentTypeMismatchException exception) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .message(exception.getMessage())
                .httpStatus(status)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, status);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDetails> uniqueViolatesConstraint(DataIntegrityViolationException exception) {

        HttpStatus status = HttpStatus.CONFLICT;

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .message(exception.getCause().getCause().getMessage())
                .httpStatus(status)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, status);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> parseError(ConstraintViolationException exception) {

        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        String message = formatConstraintViolationMessages(exception.getConstraintViolations());

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .message(message)
                .httpStatus(status)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, status);
    }

    private String formatConstraintViolationMessages(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(violation -> String.format("'%s' %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.joining(", "));
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> parseError(HttpMessageNotReadableException exception) {

        HttpStatus status = HttpStatus.CONFLICT;

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .message(exception.getMessage())
                .httpStatus(status)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, status);
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ExceptionDetails> parseError(NoResourceFoundException exception) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .message(exception.getMessage())
                .httpStatus(status)
                .timestamp(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, status);
    }
}
