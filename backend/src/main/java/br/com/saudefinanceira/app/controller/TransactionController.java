package br.com.saudefinanceira.app.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.TransactionResponse;
import br.com.saudefinanceira.app.model.Transaction;
import br.com.saudefinanceira.app.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public TransactionResponse create(
            Authentication auth,
            @Valid @RequestBody TransactionResponse req
    ) {
        String userId = auth.getName(); // email = ID lógico
        return service.create(userId, req);
    }

    @GetMapping
    public List<Transaction> list(Authentication auth) {
        return service.list(auth.getName());
    }
}
