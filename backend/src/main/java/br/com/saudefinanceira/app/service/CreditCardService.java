package br.com.saudefinanceira.app.service;

import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.dto.CreditCardRequest;
import br.com.saudefinanceira.app.dto.CreditCardResponse;
import br.com.saudefinanceira.app.model.CreditCard;
import br.com.saudefinanceira.app.repository.CreditCardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardService {

    private final CreditCardRepository repo;

    public CreditCardService(CreditCardRepository repo) {
        this.repo = repo;
    }

    public CreditCardResponse create(String userId, CreditCardRequest req) {
        CreditCard card = new CreditCard(userId, req.name(), req.brand(), req.limitAmount(), req.closingDay(), req.dueDay());
        CreditCard saved = repo.save(card);
        return toResponse(saved);
    }

    public List<CreditCardResponse> list(String userId) {
        return repo.findByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CreditCardResponse getById(String id) {
        return repo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Cartão de crédito não encontrado"));
    }

    public CreditCardResponse update(String id, CreditCardRequest req) {
        CreditCard card = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão de crédito não encontrado"));
        
        card.setName(req.name());
        card.setBrand(req.brand());
        card.setLimitAmount(req.limitAmount());
        card.setAvailableLimit(req.limitAmount());
        card.setClosingDay(req.closingDay());
        card.setDueDay(req.dueDay());
        card.setUpdatedAt(LocalDateTime.now());
        
        CreditCard updated = repo.save(card);
        return toResponse(updated);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    private CreditCardResponse toResponse(CreditCard card) {
        return new CreditCardResponse(
                card.getId(),
                card.getName(),
                card.getBrand(),
                card.getLimitAmount(),
                card.getAvailableLimit(),
                card.getClosingDay(),
                card.getDueDay(),
                card.getActive(),
                card.getCreatedAt()
        );
    }
}
