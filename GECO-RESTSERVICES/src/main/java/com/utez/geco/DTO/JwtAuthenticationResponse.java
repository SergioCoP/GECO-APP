package com.utez.geco.DTO;
import com.google.gson.JsonElement;
import com.utez.geco.model.User;
import lombok.Data;
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private User user;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
