package com.jairomatheus.movies.dto;

public record JwtPayLoadDto(
        String token,
        Long iat,
        Long exp
){}
