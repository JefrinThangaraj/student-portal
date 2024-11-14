package com.ToDo.ToDo.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private int errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
    private Boolean success;

    public CustomException( String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = httpStatus.value();
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.success = httpStatus.is2xxSuccessful();
    }


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
