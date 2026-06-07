package br.com.saudefinanceira.app.dto;

import java.time.LocalDateTime;

public record CreditCardResponse(
    String id,
    String name,
    String brand,
    Double limitAmount,
    Double availableLimit,
    Integer closingDay,
    Integer dueDay,
    Boolean active,
    LocalDateTime createdAt
) {}
