package br.com.saudefinanceira.app.dto;

import jakarta.validation.constraints.NotNull;

public record BudgetRequest(
    @NotNull Integer month,
    @NotNull Integer year,
    Double plannedIncome,
    Double plannedExpense
) {}
