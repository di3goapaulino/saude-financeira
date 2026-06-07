package br.com.saudefinanceira.app.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.LoanRequest;
import br.com.saudefinanceira.app.dto.LoanResponse;
import br.com.saudefinanceira.app.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping
    public LoanResponse create(Authentication auth, @Valid @RequestBody LoanRequest req) {
        return service.create(auth.getName(), req);
    }

    @GetMapping
    public List<LoanResponse> list(Authentication auth) {
        return service.list(auth.getName());
    }

    @GetMapping("/{id}")
    public LoanResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public LoanResponse update(@PathVariable String id, @Valid @RequestBody LoanRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
