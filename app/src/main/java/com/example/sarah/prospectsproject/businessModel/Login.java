package com.example.sarah.prospectsproject.businessModel;

import java.io.Serializable;

/**
 * Created by sarah on 10/09/2018.
 */

public class Login implements Serializable {

    private String token;
    private boolean success;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
