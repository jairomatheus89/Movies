package com.jairomatheus.movies.service;

import com.jairomatheus.movies.dto.TmdbMovieDto;
import com.jairomatheus.movies.dto.TmdbPopularResponseDto;
import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SyncService {

    private final MovieRepository repository;
    private final TmdbService tmdbservice;

    public SyncService(
            MovieRepository repository,
            TmdbService tmdbservice
    ){
        this.tmdbservice = tmdbservice;
        this.repository = repository;
    }

    public void syncPopularMovies(){

        for(int i = 1; i <= 100; i++){
            TmdbPopularResponseDto response = this.tmdbservice.getPopularMovies(i);

            for(TmdbMovieDto dto : response.results()){

                Optional<MovieEntity> existingMovie = this.repository.findByTmdbId(dto.id());

                MovieEntity movie;

                if(existingMovie.isPresent()){
                    movie = existingMovie.get();
                } else {
                    movie = new MovieEntity();
                }

                movie.setTmdbId(dto.id());
                movie.setTitle(dto.title());
                movie.setOverview(dto.overview());
                movie.setPosterPath(dto.poster_path());
                movie.setBackdropPath(dto.backdrop_path());
                movie.setReleaseDate(dto.release_date());
                movie.setVoteAverage(dto.vote_average());
                movie.setVoteCount(dto.vote_count());

                this.repository.save(movie);
            }
        }
    }
}
