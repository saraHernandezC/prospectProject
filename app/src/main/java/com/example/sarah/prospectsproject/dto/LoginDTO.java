package com.example.sarah.prospectsproject.dto;

/**
 * Created by sarah on 1/09/2018.
 */

public class LoginDTO {
    private String authToken;
    private boolean success;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String token) {
        this.authToken = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
