package com.jairomatheus.movies.controller;

import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service){
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<Page<MovieEntity>> listPopularMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ){

        Page<MovieEntity> movies = this.service.listAllMovies(page, size);
        return ResponseEntity.ok(movies);
    }
}
