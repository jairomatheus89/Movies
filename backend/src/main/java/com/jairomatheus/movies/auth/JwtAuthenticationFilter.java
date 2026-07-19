package com.jairomatheus.movies.auth;

import com.jairomatheus.movies.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(
        JwtService jwtService,
        CustomUserDetailsService customUserDetailsService
    ){
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        System.out.println("Token lido por JwtAuthFilter: " + token);

        String email = this.jwtService.extractEmail(token);

        System.out.println("Email extraído por JwtAuthFilter: " + email);

        UserDetails user = customUserDetailsService.loadUserByUsername(email);

        System.out.println("Usuario carregado: " + user);
        System.out.println("Usuario getEmail: " + user.getUsername());

        if(!jwtService.isTokenValid(token, user)){
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );


        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("USUARIO AUTHENTICADO!...");

        filterChain.doFilter(request, response);
    }
}