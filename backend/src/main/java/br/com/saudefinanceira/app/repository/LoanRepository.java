package br.com.saudefinanceira.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.saudefinanceira.app.model.Loan;

import java.util.List;

public interface LoanRepository extends MongoRepository<Loan, String> {
    List<Loan> findByUserId(String userId);
    List<Loan> findByUserIdAndStatus(String userId, String status);
}
