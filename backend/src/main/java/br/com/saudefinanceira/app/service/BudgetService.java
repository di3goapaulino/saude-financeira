package br.com.saudefinanceira.app.service;

import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.dto.BudgetRequest;
import br.com.saudefinanceira.app.dto.BudgetResponse;
import br.com.saudefinanceira.app.model.Budget;
import br.com.saudefinanceira.app.repository.BudgetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository repo;

    public BudgetService(BudgetRepository repo) {
        this.repo = repo;
    }

    public BudgetResponse create(String userId, BudgetRequest req) {
        Budget budget = new Budget(userId, req.month(), req.year(), req.plannedIncome(), req.plannedExpense());
        Budget saved = repo.save(budget);
        return toResponse(saved);
    }

    public List<BudgetResponse> list(String userId) {
        return repo.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BudgetResponse getById(String id) {
        return repo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));
    }

    public BudgetResponse getByMonthYear(String userId, Integer month, Integer year) {
        return repo.findByUserIdAndMonthAndYear(userId, month, year)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado para o período"));
    }

    public BudgetResponse update(String id, BudgetRequest req) {
        Budget budget = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));
        
        budget.setPlannedIncome(req.plannedIncome());
        budget.setPlannedExpense(req.plannedExpense());
        budget.setUpdatedAt(LocalDateTime.now());
        
        Budget updated = repo.save(budget);
        return toResponse(updated);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    private BudgetResponse toResponse(Budget budget) {
        return new BudgetResponse(
                budget.getId(),
                budget.getMonth(),
                budget.getYear(),
                budget.getPlannedIncome(),
                budget.getPlannedExpense(),
                budget.getCreatedAt()
        );
    }
}
