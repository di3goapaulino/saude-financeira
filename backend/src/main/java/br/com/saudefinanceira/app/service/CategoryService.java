package br.com.saudefinanceira.app.service;

import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.model.Category;
import br.com.saudefinanceira.app.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Category create(String userId, String name) {
        Category c = new Category(null, name, userId);
        return repo.save(c);
    }

    public List<Category> list(String userId) {
        return repo.findByUserId(userId);
    }
}
