package br.com.saudefinanceira.app.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.SubscriptionRequest;
import br.com.saudefinanceira.app.dto.SubscriptionResponse;
import br.com.saudefinanceira.app.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin(origins = "*")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public SubscriptionResponse create(Authentication auth, @Valid @RequestBody SubscriptionRequest req) {
        return service.create(auth.getName(), req);
    }

    @GetMapping
    public List<SubscriptionResponse> list(Authentication auth) {
        return service.list(auth.getName());
    }

    @GetMapping("/{id}")
    public SubscriptionResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public SubscriptionResponse update(@PathVariable String id, @Valid @RequestBody SubscriptionRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
