package com.jairomatheus.movies.service;

import com.jairomatheus.movies.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class SyncService {

    private final MovieRepository repository;

    public SyncService(MovieRepository repository){
        this.repository = repository;
    }

//    public void getPopularMovies(){
//        MovieEntity movie = new MovieEntity();
//
//        movie.setId(dto.id());
//        movie.setTmdbId(dto.tmdbId());
//        movie.setTitle(dto.title());
//        movie.setOverview(dto.overview());
//        movie.setPosterPath(dto.posterPath());
//        movie.setBackdropPath(dto.backdropPath());
//        movie.setReleaseDate(dto.releaseDate());
//        movie.setRunTime(dto.runTime());
//        movie.setVoteAverage(dto.voteAverage());
//        movie.setVoteCount(dto.voteCount());
//
//        return this.repository.save(movie);
//    }

}
