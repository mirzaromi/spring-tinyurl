package org.mirza.tinyurl.handler;

import org.mirza.tinyurl.dto.BaseResponse;
import org.mirza.tinyurl.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGlobalException(Exception ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setCode(500);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<BaseResponse<Object>> handleResourceNotFoundException(
            NotFound ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
