package com.jairomatheus.movies.service;

import com.jairomatheus.movies.dto.TmdbMovieDto;
import com.jairomatheus.movies.dto.TmdbPopularResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TmdbService {

    private final RestClient restClient;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbService(RestClient restClient){
        this.restClient = restClient;
    }

    public TmdbPopularResponseDto getPopularMovies(int page){

        return this.restClient.get()
                .uri(apiUrl + "/movie/popular?page=" + page +  "&api_key=" + apiKey)
                .retrieve()
                .body(TmdbPopularResponseDto.class)
        ;

    }
}
