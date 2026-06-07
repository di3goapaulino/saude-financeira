package br.com.saudefinanceira.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.saudefinanceira.app.model.Subscription;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    List<Subscription> findByUserId(String userId);
    List<Subscription> findByUserIdAndActive(String userId, Boolean active);
}
