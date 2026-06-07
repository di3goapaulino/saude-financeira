package br.com.saudefinanceira.app.dto;

import java.time.LocalDate;

public record CashflowProjectionResponse(
    LocalDate date,
    Double projectedBalance
) {}
