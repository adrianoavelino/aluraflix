package br.com.alura.aluraflix.controller.dto;

public class TokenResponse {
    private String token;
    private String bearer;

    public TokenResponse(String token, String bearer) {
        this.token = token;
        this.bearer = bearer;
    }

    public String getToken() {
        return token;
    }

    public String getBearer() {
        return bearer;
    }
}
