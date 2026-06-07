package br.com.saudefinanceira.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.saudefinanceira.app.model.Category;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByUserId(String userId);
}
