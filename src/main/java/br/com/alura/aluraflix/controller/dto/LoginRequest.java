package br.com.alura.aluraflix.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken converterParaUsernamePasswordAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
