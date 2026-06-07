package br.com.saudefinanceira.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoanRequest(
    @NotBlank String institution,
    @NotBlank String name,
    @NotNull Double remainingBalance,
    @NotNull Double monthlyInstallment,
    @NotNull Integer totalInstallments
) {}
