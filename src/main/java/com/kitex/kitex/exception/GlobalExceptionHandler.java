package com.kitex.kitex.exception;

import com.kitex.kitex.exception.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDto> handleConflictException(final ConflictException e) {
        return     new ResponseEntity<ErrorDto>(ErrorDto.builder()
                .withTitle("Conflict")
                .withStatus(HttpStatus.CONFLICT.value())
                .withDetail(e.getMessage())
                .withErrorCode("409")
                .withErrorType(NotFoundException.class.getSimpleName())
                .build(), HttpStatus.CONFLICT);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleSystemBadRequestException(final MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return     new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(final BadRequestException e) {
        return     new ResponseEntity<ErrorDto>(ErrorDto.builder()
                .withTitle("Bad Request")
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withDetail(e.getMessage())
                .withErrorCode("400")
                .withErrorType(BadRequestException.class.getSimpleName())
                .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorDto> handleUnAuthorizedException(final UnAuthorizedException e) {
        return     new ResponseEntity<ErrorDto>(ErrorDto.builder()
                .withTitle("UnAuthorized Request")
                .withStatus(HttpStatus.UNAUTHORIZED.value())
                .withDetail(e.getMessage())
                .withErrorCode("401")
                .withErrorType(UnAuthorizedException.class.getSimpleName())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorDto> handleUnForbiddenException(final ForbiddenException e) {
        return     new ResponseEntity<ErrorDto>(ErrorDto.builder()
                .withTitle("Forbidden Request")
                .withStatus(HttpStatus.FORBIDDEN.value())
                .withDetail(e.getMessage())
                .withErrorCode("403")
                .withErrorType(ForbiddenException.class.getSimpleName())
                .build(), HttpStatus.FORBIDDEN);
    }
}
