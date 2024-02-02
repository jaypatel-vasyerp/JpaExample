package com.jayptl.one_to_one_mapping_exp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Object EntityNotFoundException(Exception exception, WebRequest request){
        return new ExceptionMessage(exception.getMessage());

    }
}
