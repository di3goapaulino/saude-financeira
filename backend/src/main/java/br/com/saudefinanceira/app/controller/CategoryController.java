package br.com.saudefinanceira.app.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.model.Category;
import br.com.saudefinanceira.app.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Category create(
            Authentication auth,
            @RequestParam @NotBlank String name
    ) {
        return service.create(auth.getName(), name);
    }

    @GetMapping
    public List<Category> list(Authentication auth) {
        return service.list(auth.getName());
    }
}
