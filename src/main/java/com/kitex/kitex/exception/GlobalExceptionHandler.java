package com.kitex.kitex.exception;

import com.kitex.kitex.exception.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(final ConflictException e) {
        return     new ResponseEntity<ErrorDto>(ErrorDto.builder()
                .withTitle("Not found")
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withDetail(e.getMessage())
                .withErrorCode("404")
                .withErrorType(NotFoundException.class.getSimpleName())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleConflictException(final ConflictException e) {
        return     new ResponseEntity<ErrorDto>(ErrorDto.builder()
                .withTitle("Conflict")
                .withStatus(HttpStatus.CONFLICT.value())
                .withDetail(e.getMessage())
                .withErrorCode("409")
                .withErrorType(NotFoundException.class.getSimpleName())
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleBadRequestException(final BadRequestException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return     new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
