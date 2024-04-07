package com.tv.show.teleservice.api.exception;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity(new RestErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(Exception ex) {
        return new ResponseEntity(new RestErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), Collections.singletonList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handlerRuntimeException(Exception ex) {
        return new ResponseEntity(new RestErrorResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), Collections.singletonList(ex.getMessage())), HttpStatus.NOT_FOUND);
    }

}
