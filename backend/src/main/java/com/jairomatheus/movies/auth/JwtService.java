package com.jairomatheus.movies.auth;

import com.jairomatheus.movies.entity.UserEntity;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserEntity user){
        return Jwts.builder()
            .subject(user.getEmail())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignKey())
            .compact();
    }

    public SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails user){
        String email = extractEmail(token);

        return email.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }
}
