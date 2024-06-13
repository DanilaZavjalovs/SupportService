package org.example.supportservice.exceptionHandler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@Builder
public class ExceptionDetails {
    private final HttpStatus httpStatus;
    private final String message;
    private final ZonedDateTime timestamp;
}
