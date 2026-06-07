package br.com.saudefinanceira.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreditCardRequest(
    @NotBlank String name,
    @NotBlank String brand,
    @NotNull Double limitAmount,
    @NotNull Integer closingDay,
    @NotNull Integer dueDay
) {}
