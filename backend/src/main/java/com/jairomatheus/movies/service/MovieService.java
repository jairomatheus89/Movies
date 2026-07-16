package com.jairomatheus.movies.service;

import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository){
        this.repository = repository;
    }

    public Page<MovieEntity> listAllMovies(int page, int size){
        return repository.findAll(PageRequest.of(page, size));
    }
}
