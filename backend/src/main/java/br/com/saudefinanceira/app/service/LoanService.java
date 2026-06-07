package br.com.saudefinanceira.app.service;

import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.dto.LoanRequest;
import br.com.saudefinanceira.app.dto.LoanResponse;
import br.com.saudefinanceira.app.model.Loan;
import br.com.saudefinanceira.app.repository.LoanRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository repo;

    public LoanService(LoanRepository repo) {
        this.repo = repo;
    }

    public LoanResponse create(String userId, LoanRequest req) {
        Loan loan = new Loan(userId, req.institution(), req.name(), req.remainingBalance(), req.monthlyInstallment(), req.totalInstallments());
        Loan saved = repo.save(loan);
        return toResponse(saved);
    }

    public List<LoanResponse> list(String userId) {
        return repo.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public LoanResponse getById(String id) {
        return repo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
    }

    public LoanResponse update(String id, LoanRequest req) {
        Loan loan = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        
        loan.setName(req.name());
        loan.setInstitution(req.institution());
        loan.setRemainingBalance(req.remainingBalance());
        loan.setMonthlyInstallment(req.monthlyInstallment());
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan updated = repo.save(loan);
        return toResponse(updated);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    private LoanResponse toResponse(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getInstitution(),
                loan.getName(),
                loan.getRemainingBalance(),
                loan.getMonthlyInstallment(),
                loan.getTotalInstallments(),
                loan.getCurrentInstallment(),
                loan.getRemainingInstallments(),
                loan.getStatus(),
                loan.getCreatedAt()
        );
    }
}
