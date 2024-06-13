package org.example.supportservice.exceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void noSuchElement() {
        NoSuchElementException exception = new NoSuchElementException("Test message");
        ResponseEntity<ExceptionDetails> response = handler.noSuchElement(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Test message", response.getBody().getMessage());
    }

    @Test
    void invalidId() {
        MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("Test", String.class, "param", null, null);
        ResponseEntity<ExceptionDetails> response = handler.invalidId(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(exception.getMessage(), response.getBody().getMessage());
    }

    @Test
    void uniqueViolatesConstraint() {
        DataIntegrityViolationException exception = mock(DataIntegrityViolationException.class);
        Throwable cause = new Throwable("Test message");
        Throwable rootCause = new Throwable(cause);
        when(exception.getCause()).thenReturn(rootCause);
        ResponseEntity<ExceptionDetails> response = handler.uniqueViolatesConstraint(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Test message", response.getBody().getMessage());
    }

    @Test
    void parseErrorConstraintViolation() {
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        when(exception.getConstraintViolations()).thenReturn(Set.of(mock(ConstraintViolation.class)));
        ResponseEntity<ExceptionDetails> response = handler.parseError(exception);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    void parseErrorNoResourceFound() {
        String message = "Test message";
        NoResourceFoundException exception = new NoResourceFoundException(HttpMethod.GET, message);
        ResponseEntity<ExceptionDetails> response = handler.parseError(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No static resource " + message + ".", response.getBody().getMessage());
    }
}