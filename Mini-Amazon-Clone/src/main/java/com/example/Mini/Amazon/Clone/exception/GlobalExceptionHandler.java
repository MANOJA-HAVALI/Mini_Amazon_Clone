package com.example.Mini.Amazon.Clone.exception;

import com.example.Mini.Amazon.Clone.dto.requests.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalExc(Exception e) {
        log.error(e.getMessage());
        ApiResponse<String> errordetails = new ApiResponse<>("false", e.getMessage());
        return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream().collect(Collectors.toMap(
                        err -> err.getField(),
                        err -> err.getDefaultMessage(),
                        (existing, replacement) -> existing
                ));

        ApiResponse<Map<String, String>> errordetails = new ApiResponse<>("false",errors);
        return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);
    }



}
