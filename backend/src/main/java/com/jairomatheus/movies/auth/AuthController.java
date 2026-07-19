package com.jairomatheus.movies.auth;

import com.jairomatheus.movies.dto.*;
import com.jairomatheus.movies.exceptions.InvalidAuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegisterDto dto){
        return ResponseEntity.ok(this.service.registerUser(dto.name(), dto.email(), dto.password()));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginDto dto){
        return ResponseEntity.ok(this.service.loginUser(dto.email(), dto.password()));
    }

}