package com.jairomatheus.movies.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TmdbMovieDto(

    Integer id,
    String title,
    String overview,
    String poster_path,
    String backdrop_path,
    LocalDate release_date,
    BigDecimal vote_average,
    Integer vote_count
){}


