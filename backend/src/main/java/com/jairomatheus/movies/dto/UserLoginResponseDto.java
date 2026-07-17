package com.jairomatheus.movies.dto;

public record UserLoginResponseDto(
        String name,
        String email,
        String token
){}
