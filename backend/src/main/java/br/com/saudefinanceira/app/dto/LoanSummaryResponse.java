package br.com.saudefinanceira.app.dto;

public record LoanSummaryResponse(
    Double totalDebt,
    Integer activeLoans,
    Double monthlyCommitment
) {}
