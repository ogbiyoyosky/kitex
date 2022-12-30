package com.kitex.kitex.exception;

import com.kitex.kitex.exception.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(final NotFoundException e) {
        return     new ResponseEntity<ErrorDto>(ErrorDto.builder()
                .withTitle("Not found")
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withDetail(e.getMessage())
                .withErrorCode("404")
                .withErrorType(NotFoundException.class.getSimpleName())
                .build(), HttpStatus.NOT_FOUND);
    }
}
