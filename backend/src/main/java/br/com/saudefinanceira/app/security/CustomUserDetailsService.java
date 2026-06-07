package br.com.saudefinanceira.app.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.com.saudefinanceira.app.model.User;
import br.com.saudefinanceira.app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRole())  // role: "USER" ou "ADMIN"
                .build();
    }
}