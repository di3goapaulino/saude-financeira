package br.com.saudefinanceira.app.service;

import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.dto.SubscriptionRequest;
import br.com.saudefinanceira.app.dto.SubscriptionResponse;
import br.com.saudefinanceira.app.model.Subscription;
import br.com.saudefinanceira.app.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final SubscriptionRepository repo;

    public SubscriptionService(SubscriptionRepository repo) {
        this.repo = repo;
    }

    public SubscriptionResponse create(String userId, SubscriptionRequest req) {
        Subscription sub = new Subscription(userId, req.name(), req.amount(), req.billingDay());
        Subscription saved = repo.save(sub);
        return toResponse(saved);
    }

    public List<SubscriptionResponse> list(String userId) {
        return repo.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<SubscriptionResponse> listActive(String userId) {
        return repo.findByUserIdAndActive(userId, true).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SubscriptionResponse getById(String id) {
        return repo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
    }

    public SubscriptionResponse update(String id, SubscriptionRequest req) {
        Subscription sub = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
        
        sub.setName(req.name());
        sub.setAmount(req.amount());
        sub.setBillingDay(req.billingDay());
        sub.setUpdatedAt(LocalDateTime.now());
        
        Subscription updated = repo.save(sub);
        return toResponse(updated);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    private SubscriptionResponse toResponse(Subscription sub) {
        return new SubscriptionResponse(
                sub.getId(),
                sub.getName(),
                sub.getAmount(),
                sub.getBillingDay(),
                sub.getActive(),
                sub.getCreatedAt()
        );
    }
}
