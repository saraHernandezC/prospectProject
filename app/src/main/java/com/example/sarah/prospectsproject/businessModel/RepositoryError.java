package com.example.sarah.prospectsproject.businessModel;


/**
 * Created by sarah on 11/09/2018.
 */

public class RepositoryError extends Exception {
    private int code;
    private String error;

    public RepositoryError(String message) {
        super(message);
    }

    public RepositoryError() {
    }

    public int getIdError() {
        return code;
    }

    public void setIdError(int idError) {
        this.code = idError;
    }

    public String getMensaje() {
        return error;
    }

    public void setMensaje(String mensaje) {
        error = mensaje;
    }
}
