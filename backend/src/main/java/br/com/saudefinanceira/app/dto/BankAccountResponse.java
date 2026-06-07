package br.com.saudefinanceira.app.dto;

import java.time.LocalDateTime;

public record BankAccountResponse(
    String id,
    String name,
    String bank,
    Double currentBalance,
    Boolean isPrimary,
    LocalDateTime createdAt
) {}
