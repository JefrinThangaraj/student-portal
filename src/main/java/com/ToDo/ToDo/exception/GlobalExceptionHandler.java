package com.ToDo.ToDo.exception;

import com.ToDo.ToDo.dto.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GenericResponse> handleCustomException(CustomException ex) {
        GenericResponse response = new GenericResponse(
                ex.getErrorCode(),
                ex.getErrorMessage(),
                ex.getSuccess(),
                null
                );
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}
