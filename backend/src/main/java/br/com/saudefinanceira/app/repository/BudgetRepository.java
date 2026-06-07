package br.com.saudefinanceira.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.saudefinanceira.app.model.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends MongoRepository<Budget, String> {
    List<Budget> findByUserId(String userId);
    Optional<Budget> findByUserIdAndMonthAndYear(String userId, Integer month, Integer year);
}
