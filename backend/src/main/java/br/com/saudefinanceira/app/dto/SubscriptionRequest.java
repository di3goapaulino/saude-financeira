package br.com.saudefinanceira.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscriptionRequest(
    @NotBlank String name,
    @NotNull Double amount,
    @NotNull Integer billingDay
) {}
