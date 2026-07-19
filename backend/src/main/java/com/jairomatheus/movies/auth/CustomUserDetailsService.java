package com.jairomatheus.movies.auth;

import com.jairomatheus.movies.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    public CustomUserDetailsService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
    }
}
