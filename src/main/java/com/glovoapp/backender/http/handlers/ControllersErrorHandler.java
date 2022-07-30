package com.glovoapp.backender.http.handlers;

import com.glovoapp.backender.http.responses.ErrorResponse;
import org.joda.time.DateTime;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class ControllersErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new ErrorResponse(
                        DateTime.now().toDate(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation Failed",
                        errors
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        DateTime.now().toDate(),
                        status.value(),
                        "Request parsing failed",
                        Collections.singletonList(ex.getMessage())
                ),
                status
        );
    }

    @ExceptionHandler(HttpException.class)
    protected ResponseEntity<Object> handleHttpException(HttpException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        DateTime.now().toDate(),
                        ex.getStatus().value(),
                        ex.getMessage(),
                        Collections.emptyList()
                ),
                ex.getStatus()
        );
    }
}

