package com.jairomatheus.movies.controller;

import com.jairomatheus.movies.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SyncController {

    private final SyncService service;

    public SyncController(SyncService service){
        this.service = service;
    }

    @GetMapping("/syncMovies")
    public ResponseEntity<String> tmdbTest(){

        this.service.syncPopularMovies();
        return ResponseEntity.ok("FILMES SINCRONIZADOS COM SUCESSO!");
    }
}