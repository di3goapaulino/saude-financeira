package br.com.saudefinanceira.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.saudefinanceira.app.model.CreditCard;

import java.util.List;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
    List<CreditCard> findByUserId(String userId);
}
