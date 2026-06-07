package br.com.saudefinanceira.app.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.BudgetRequest;
import br.com.saudefinanceira.app.dto.BudgetResponse;
import br.com.saudefinanceira.app.service.BudgetService;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping
    public BudgetResponse create(Authentication auth, @Valid @RequestBody BudgetRequest req) {
        return service.create(auth.getName(), req);
    }

    @GetMapping
    public List<BudgetResponse> list(Authentication auth) {
        return service.list(auth.getName());
    }

    @GetMapping("/{id}")
    public BudgetResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/month/{month}/year/{year}")
    public BudgetResponse getByMonthYear(Authentication auth, @PathVariable Integer month, @PathVariable Integer year) {
        return service.getByMonthYear(auth.getName(), month, year);
    }

    @PutMapping("/{id}")
    public BudgetResponse update(@PathVariable String id, @Valid @RequestBody BudgetRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
