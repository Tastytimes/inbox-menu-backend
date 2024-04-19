package com.rs.listing.Restuarant.listing.DTO;

import java.util.List;

public class LoginResDto {

    private int status;
    private String message;

   private String token;
   private Boolean FirstTimeLogin;

    public LoginResDto(){}
    public LoginResDto(int status, String message, String token, Boolean firstTimeLogin) {
        this.status = status;
        this.message = message;
        this.token = token;
        FirstTimeLogin = firstTimeLogin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getFirstTimeLogin() {
        return FirstTimeLogin;
    }

    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        FirstTimeLogin = firstTimeLogin;
    }

    @Override
    public String toString() {
        return "LoginResDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", FirstTimeLogin=" + FirstTimeLogin +
                '}';
    }
}
