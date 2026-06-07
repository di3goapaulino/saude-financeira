package br.com.saudefinanceira.app.dto;

public record AuthResponse(
        String token,
        String email,
        String role
) {}
