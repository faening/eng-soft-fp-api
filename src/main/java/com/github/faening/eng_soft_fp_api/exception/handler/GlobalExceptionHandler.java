package com.github.faening.eng_soft_fp_api.exception.handler;

import com.github.faening.eng_soft_fp_api.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final String formattedDateTime = LocalDateTime.now().format(formatter);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(
            formattedDateTime,
            ex.getMessage(),
            request.getDescription(false)
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(
            formattedDateTime,
            ex.getMessage(),
            request.getDescription(false)
        ), HttpStatus.BAD_REQUEST);
    }
}