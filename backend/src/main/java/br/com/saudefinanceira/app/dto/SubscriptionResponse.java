package br.com.saudefinanceira.app.dto;

import java.time.LocalDateTime;

public record SubscriptionResponse(
    String id,
    String name,
    Double amount,
    Integer billingDay,
    Boolean active,
    LocalDateTime createdAt
) {}
