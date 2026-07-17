package com.jairomatheus.movies.service;

import com.jairomatheus.movies.dto.UserLoginResponseDto;
import com.jairomatheus.movies.dto.UserResponseDto;
import com.jairomatheus.movies.entity.UserEntity;
import com.jairomatheus.movies.exceptions.EmailAlreadyExistsException;
import com.jairomatheus.movies.exceptions.InvalidAuthenticationException;
import com.jairomatheus.movies.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto registerUser(String name, String email, String password){

        if(this.repository.existsByEmail(email)){
            throw new EmailAlreadyExistsException("ESSE EMAIL JA ESTÁ CADASTRADO!");
        }

        UserEntity user = new UserEntity();

        user.setName(name);
        user.setEmail(email);

        String passwordHash = passwordEncoder.encode(password);
        user.setPassword(passwordHash);

        try{
            this.repository.save(user);
            return new UserResponseDto(user.getName(), user.getEmail());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public UserLoginResponseDto loginUser(String email, String password){

        UserEntity user = this.repository.findByEmail(email)
            .orElseThrow(() -> new InvalidAuthenticationException("Dados Invalidos!..."));

        if(!this.passwordEncoder.matches(password, user.getPassword())){
            throw new InvalidAuthenticationException("Falha na autenticação: SENHA INVALIDA!...");
        }

        return new UserLoginResponseDto(user.getName(), user.getEmail(), "LOGIN REALIZADO COM SUCESSO!");
    }
}
