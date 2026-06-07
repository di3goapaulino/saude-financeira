package br.com.saudefinanceira.app.service;

import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.dto.BankAccountRequest;
import br.com.saudefinanceira.app.dto.BankAccountResponse;
import br.com.saudefinanceira.app.model.BankAccount;
import br.com.saudefinanceira.app.repository.BankAccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final BankAccountRepository repo;

    public BankAccountService(BankAccountRepository repo) {
        this.repo = repo;
    }

    public BankAccountResponse create(String userId, BankAccountRequest req) {
        BankAccount acc = new BankAccount(userId, req.bank(), req.name(), req.currentBalance());
        BankAccount saved = repo.save(acc);
        return toResponse(saved);
    }

    public List<BankAccountResponse> list(String userId) {
        return repo.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BankAccountResponse getById(String id) {
        return repo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada"));
    }

    public BankAccountResponse update(String id, BankAccountRequest req) {
        BankAccount acc = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada"));
        
        acc.setName(req.name());
        acc.setBank(req.bank());
        acc.setCurrentBalance(req.currentBalance());
        acc.setUpdatedAt(LocalDateTime.now());
        
        BankAccount updated = repo.save(acc);
        return toResponse(updated);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    private BankAccountResponse toResponse(BankAccount acc) {
        return new BankAccountResponse(
                acc.getId(),
                acc.getName(),
                acc.getBank(),
                acc.getCurrentBalance(),
                acc.getIsPrimary(),
                acc.getCreatedAt()
        );
    }
}
