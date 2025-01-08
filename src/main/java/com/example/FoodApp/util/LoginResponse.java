package com.example.FoodApp.util;

public class LoginResponse {
    String message;
    Boolean status;
    int code;

    public LoginResponse(String message, Boolean status,int code) {
        this.message = message;
        this.status = status;
        this.code=code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
