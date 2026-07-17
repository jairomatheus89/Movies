package com.jairomatheus.movies.controller;

import com.jairomatheus.movies.dto.UserLoginDto;
import com.jairomatheus.movies.dto.UserLoginResponseDto;
import com.jairomatheus.movies.dto.UserRegisterDto;
import com.jairomatheus.movies.dto.UserResponseDto;
import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.entity.UserEntity;
import com.jairomatheus.movies.service.MovieService;
import com.jairomatheus.movies.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
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