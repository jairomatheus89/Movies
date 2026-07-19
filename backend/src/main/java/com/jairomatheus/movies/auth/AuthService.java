package com.jairomatheus.movies.auth;

import com.jairomatheus.movies.dto.JwtPayLoadDto;
import com.jairomatheus.movies.dto.UserLoginResponseDto;
import com.jairomatheus.movies.dto.UserResponseDto;
import com.jairomatheus.movies.entity.UserEntity;
import com.jairomatheus.movies.exceptions.EmailAlreadyExistsException;
import com.jairomatheus.movies.exceptions.InvalidAuthenticationException;
import com.jairomatheus.movies.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

        String token = jwtService.generateToken(user);
        return new UserLoginResponseDto(
                user.getName(),
                user.getEmail(),
                new JwtPayLoadDto(
                    token,
                    jwtService.extractAllClaims(token).getIssuedAt().getTime() / 1000,
                    jwtService.extractAllClaims(token).getExpiration().getTime() / 1000
                )
        );
    }






}
