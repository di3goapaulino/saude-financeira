package br.com.saudefinanceira.app.service;

import br.com.saudefinanceira.app.dto.TransactionResponse;
import br.com.saudefinanceira.app.model.Transaction;
import br.com.saudefinanceira.app.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public TransactionResponse create(String userId, TransactionResponse req) {

        Transaction t = new Transaction(null, userId, req.categoryId(), req.description(), req.amount(), req.type(), req.date());

        repo.save(t);

        return new TransactionResponse(
                t.getId(),
                t.getDescription(),
                t.getAmount(),
                t.getType(),
                t.getCategoryId(),
                t.getDate()
        );
    }

    public List<Transaction> list(String userId) {
        return repo.findByUserId(userId);
    }
}
