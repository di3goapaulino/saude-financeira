package br.com.saudefinanceira.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BankAccountRequest(
    @NotBlank String name,
    @NotBlank String bank,
    @NotNull Double currentBalance
) {}
