package br.com.saudefinanceira.app.dto;

import java.time.LocalDate;

public record TransactionResponse(
        String id,
        String description,
        Double amount,
        String type,
        String categoryId,
        LocalDate date
) {}
