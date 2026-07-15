package com.jairomatheus.movies.controller;

import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.service.MovieService;
import com.jairomatheus.movies.service.TmdbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;
    private final TmdbService tmdbService;

    public MovieController(MovieService service, TmdbService tmdbService){
        this.service = service;
        this.tmdbService = tmdbService;
    }

    @GetMapping("/movie")
    public ResponseEntity<List<MovieEntity>> listAllMovies(){
        List<MovieEntity> listinha = this.service.listAllMovies();
        System.out.println(listinha);
        return ResponseEntity.ok(listinha);
    }

    @GetMapping("/testTmdb")
    public ResponseEntity<String> tmdbTest(){

        System.out.println("TA CEHGANDO CARAIO?");
        return ResponseEntity.ok(this.tmdbService.getPopularMovies());
    }
}
