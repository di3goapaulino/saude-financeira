package br.com.saudefinanceira.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.CashflowProjectionResponse;
import br.com.saudefinanceira.app.dto.DashboardSummaryResponse;
import br.com.saudefinanceira.app.dto.LoanSummaryResponse;
import br.com.saudefinanceira.app.service.DashboardService;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary(Authentication auth) {
        return service.getSummary(auth.getName());
    }

    @GetMapping("/cashflow")
    public List<CashflowProjectionResponse> getCashflow(Authentication auth) {
        return service.getCashflowProjection(auth.getName());
    }

    @GetMapping("/loans/summary")
    public LoanSummaryResponse getLoansSummary(Authentication auth) {
        return service.getLoanSummary(auth.getName());
    }
}
