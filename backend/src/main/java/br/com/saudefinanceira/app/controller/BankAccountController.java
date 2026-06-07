package br.com.saudefinanceira.app.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.BankAccountRequest;
import br.com.saudefinanceira.app.dto.BankAccountResponse;
import br.com.saudefinanceira.app.service.BankAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class BankAccountController {

    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @PostMapping
    public BankAccountResponse create(Authentication auth, @Valid @RequestBody BankAccountRequest req) {
        return service.create(auth.getName(), req);
    }

    @GetMapping
    public List<BankAccountResponse> list(Authentication auth) {
        return service.list(auth.getName());
    }

    @GetMapping("/{id}")
    public BankAccountResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public BankAccountResponse update(@PathVariable String id, @Valid @RequestBody BankAccountRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
