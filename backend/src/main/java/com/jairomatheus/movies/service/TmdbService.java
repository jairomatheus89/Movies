package com.jairomatheus.movies.service;

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

    public String getPopularMovies(){



        return this.restClient.get()
                .uri(apiUrl + "/movie/popular?api_key=" + apiKey)
                .retrieve()
                .body(String.class)
        ;
    }
}
