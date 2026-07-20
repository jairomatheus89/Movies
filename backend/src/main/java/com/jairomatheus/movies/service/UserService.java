package com.jairomatheus.movies.service;

import com.jairomatheus.movies.dto.AddFavMovieResponseDto;
import com.jairomatheus.movies.dto.SaveFavoriteMovieDto;
import com.jairomatheus.movies.dto.UserProfileDataDto;
import com.jairomatheus.movies.entity.FavoriteEntity;
import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.entity.UserEntity;
import com.jairomatheus.movies.exceptions.FavoriteMovieException;
import com.jairomatheus.movies.repositories.FavoriteRepository;
import com.jairomatheus.movies.repositories.MovieRepository;
import com.jairomatheus.movies.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;

    public UserService(
        UserRepository repository,
        FavoriteRepository favoriteRepository,
        MovieRepository movieRepository
    ){
        this.repository = repository;
        this.favoriteRepository = favoriteRepository;
        this.movieRepository = movieRepository;
    }

    public UserProfileDataDto showUserProfileData(UserDetails userDetails){

        UserEntity user = (UserEntity) userDetails;

        String message;

        if(user.getName().startsWith("marina")){
            message = String.format("OI MARINA AMOR DA MINHA VIDA <3");
        } else {
            message = String.format("Olá meu caro %s", user.getName());
        }

        return new UserProfileDataDto(user.getName(), user.getEmail(), message);
    }

    public AddFavMovieResponseDto saveFavMovieIntoProfile(UserDetails userDetails, SaveFavoriteMovieDto dto){
        UserEntity user = (UserEntity) userDetails;

        FavoriteEntity favorite = new FavoriteEntity();

        MovieEntity movieExisting = this.movieRepository.findById(dto.movieId())
                .orElseThrow(() -> new FavoriteMovieException("FILME NAO ENCONTRADO"));

        favorite.setUserId(user);
        favorite.setMovieId(movieExisting);

        try{
            this.favoriteRepository.save(favorite);
        } catch (DataIntegrityViolationException e) {
            throw new FavoriteMovieException("ESSE FILME JA FOI FAVORITADO MEU CHAPS");
        }

        return new AddFavMovieResponseDto("FILME FAVORITADO COM SUCESSO!");
    }

    public Page<MovieEntity> listMyFavorites(UserDetails userDetails, Pageable pageable){
        UserEntity user = (UserEntity) userDetails;

        Page<FavoriteEntity> favorites = this.favoriteRepository.findByUserId(user, pageable);


        return favorites.map(FavoriteEntity::getMovieId);
    }

}
