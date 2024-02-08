package com.jayptl.one_to_one_mapping_exp.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jayptl.one_to_one_mapping_exp.dto.ResponseDto;
import com.jayptl.one_to_one_mapping_exp.exception.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Object EntityNotFoundExceptionHandling(EntityNotFoundException exception){
        return new ResponseDto(404,"Error",exception.getMessage());

    }
}
