package com.jairomatheus.movies.controller;

import com.jairomatheus.movies.service.MovieService;
import com.jairomatheus.movies.service.SyncService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sync")
public class SyncController {

    private final SyncService service;

    public SyncController(SyncService service){
        this.service = service;
    }

}