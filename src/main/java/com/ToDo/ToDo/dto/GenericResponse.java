package com.ToDo.ToDo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class GenericResponse {


    private String message;
    private String errorMessage;
    private  int code;
    private Object data;
    private Boolean success;

    public GenericResponse(String message, Boolean success, int code) {
        this.message = message;
        this.success = success;
        this.code = code;
    }

    public GenericResponse(int code, String errorMessage, Boolean success, String message) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.success = success;
        this.message = message;
    }
    public GenericResponse(Object data) {
        this.data = data;
        this.code = 200;
        this.message = "Success";
        this.success=true;
    }

}
