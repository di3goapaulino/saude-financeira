package br.com.saudefinanceira.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.saudefinanceira.app.model.Transaction;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUserId(String userId);
}
