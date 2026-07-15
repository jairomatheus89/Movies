package com.jairomatheus.movies.service;

import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.repositories.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository){
        this.repository = repository;
    }

    public List<MovieEntity> listAllMovies(){
        return repository.findAll();
    }
}
