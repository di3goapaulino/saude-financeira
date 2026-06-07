package br.com.saudefinanceira.app.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.CreditCardRequest;
import br.com.saudefinanceira.app.dto.CreditCardResponse;
import br.com.saudefinanceira.app.service.CreditCardService;

import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
@CrossOrigin(origins = "*")
public class CreditCardController {

    private final CreditCardService service;

    public CreditCardController(CreditCardService service) {
        this.service = service;
    }

    @PostMapping
    public CreditCardResponse create(Authentication auth, @Valid @RequestBody CreditCardRequest req) {
        return service.create(auth.getName(), req);
    }

    @GetMapping
    public List<CreditCardResponse> list(Authentication auth) {
        return service.list(auth.getName());
    }

    @GetMapping("/{id}")
    public CreditCardResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CreditCardResponse update(@PathVariable String id, @Valid @RequestBody CreditCardRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
