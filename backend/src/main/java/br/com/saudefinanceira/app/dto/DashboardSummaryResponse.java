package br.com.saudefinanceira.app.dto;

public record DashboardSummaryResponse(
    Double currentBalance,
    Double totalDebt,
    Double creditCardDebt,
    Double monthlySubscriptions,
    Double monthlyFixedExpenses,
    Double dailyAvailableAmount
) {}
