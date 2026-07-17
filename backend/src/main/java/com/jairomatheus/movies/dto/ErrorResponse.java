package com.jairomatheus.movies.dto;

public record ErrorResponse(
        int status,
        String message
) {
}
