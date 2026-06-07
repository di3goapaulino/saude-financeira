package br.com.saudefinanceira.app.dto;

import java.time.LocalDateTime;

public record BudgetResponse(
    String id,
    Integer month,
    Integer year,
    Double plannedIncome,
    Double plannedExpense,
    LocalDateTime createdAt
) {}
