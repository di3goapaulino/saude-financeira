package br.com.saudefinanceira.app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.dto.AuthRequest;
import br.com.saudefinanceira.app.dto.AuthResponse;
import br.com.saudefinanceira.app.model.User;
import br.com.saudefinanceira.app.repository.UserRepository;
import br.com.saudefinanceira.app.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtUtil jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @SuppressWarnings("null")
    public AuthResponse register(AuthRequest req) {

        if (repo.findByEmail(req.email()).isPresent())
            throw new RuntimeException("Usuário já existe.");

        User user = new User(null, req.email(), encoder.encode(req.password()), "USER");

        repo.save(user);

        String token = jwt.generateToken(user.getEmail(), user.getRole());

        return new AuthResponse(token, user.getEmail(), user.getRole());
    }


    public AuthResponse login(AuthRequest req) {
        User user = repo.findByEmail(req.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (!encoder.matches(req.password(), user.getPassword())) {
            throw new RuntimeException("Senha inválida.");
        }

        String token = jwt.generateToken(user.getEmail(), user.getRole());

        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}
