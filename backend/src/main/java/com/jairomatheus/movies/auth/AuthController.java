package com.jairomatheus.movies.auth;

import com.jairomatheus.movies.dto.UserLoginDto;
import com.jairomatheus.movies.dto.UserLoginResponseDto;
import com.jairomatheus.movies.dto.UserRegisterDto;
import com.jairomatheus.movies.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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