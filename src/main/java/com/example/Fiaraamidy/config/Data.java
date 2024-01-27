package com.example.Fiaraamidy.config;


import com.example.Fiaraamidy.entites.Utilisateur;

public class Data {
    private String idUser;
    private String token;
    private String message;

    public Data() {
    }

    public Data(String user, String token, String message) {
        this.idUser = user;
        this.token = token;
        this.message = message;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
