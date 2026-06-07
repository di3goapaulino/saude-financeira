package br.com.saudefinanceira.app.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import br.com.saudefinanceira.app.dto.AuthRequest;
import br.com.saudefinanceira.app.dto.AuthResponse;
import br.com.saudefinanceira.app.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody AuthRequest req) {
        return service.register(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest req) {
        return service.login(req);
    }
}
