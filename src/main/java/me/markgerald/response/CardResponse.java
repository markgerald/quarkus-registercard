package me.markgerald.response;


import jakarta.json.bind.annotation.JsonbProperty;

public class CardResponse {

    @JsonbProperty("token")
    private String token;

    public CardResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
