package com.kitex.kitex.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class BadRequestException extends MethodArgumentNotValidException {
    public BadRequestException(MethodParameter parameter, BindingResult bindingResult) {
        super( parameter, bindingResult);
    }
}