package com.ToDo.ToDo.dto;

import com.ToDo.ToDo.model.Task;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dto<T> {
    private String message;
    private boolean success;
    private T data;

    public Dto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Dto(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    @Data
    public static class TaskRequest {
        private Task task;
        private String categoryId;
    }

    @Data
    public static class AuthRequest {
        private String username;
        private String password;
    }

    @Data
    public static class AuthResponse {
        private String accessToken;
        private String refreshToken;

        public AuthResponse(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Data
    public static class RefreshTokenRequest {
        private String refreshToken;
    }
}
