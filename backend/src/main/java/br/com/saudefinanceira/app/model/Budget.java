package br.com.saudefinanceira.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "budgets")
public class Budget {
    @Id
    private String id;

    private String userId;
    private Integer month;
    private Integer year;
    private Double plannedIncome;
    private Double plannedExpense;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Budget() {
    }

    public Budget(String userId, Integer month, Integer year, Double plannedIncome, Double plannedExpense) {
        this.userId = userId;
        this.month = month;
        this.year = year;
        this.plannedIncome = plannedIncome;
        this.plannedExpense = plannedExpense;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPlannedIncome() {
        return plannedIncome;
    }

    public void setPlannedIncome(Double plannedIncome) {
        this.plannedIncome = plannedIncome;
    }

    public Double getPlannedExpense() {
        return plannedExpense;
    }

    public void setPlannedExpense(Double plannedExpense) {
        this.plannedExpense = plannedExpense;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
