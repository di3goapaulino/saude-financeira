package br.com.saudefinanceira.app.dto;

import java.time.LocalDateTime;

public record LoanResponse(
    String id,
    String institution,
    String name,
    Double remainingBalance,
    Double monthlyInstallment,
    Integer totalInstallments,
    Integer currentInstallment,
    Integer remainingInstallments,
    String status,
    LocalDateTime createdAt
) {}
