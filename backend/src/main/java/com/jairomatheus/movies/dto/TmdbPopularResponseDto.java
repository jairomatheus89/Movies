package com.jairomatheus.movies.dto;

import java.util.List;

public record TmdbPopularResponseDto (
    Integer page,
    List<TmdbMovieDto> results,
    Integer total_pages,
    Integer total_results
){}
