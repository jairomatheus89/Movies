package com.jairomatheus.movies.controller;

import com.jairomatheus.movies.dto.AddFavMovieResponseDto;
import com.jairomatheus.movies.dto.SaveFavoriteMovieDto;
import com.jairomatheus.movies.dto.UserProfileDataDto;
import com.jairomatheus.movies.entity.MovieEntity;
import com.jairomatheus.movies.entity.UserEntity;
import com.jairomatheus.movies.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDataDto> userProfile(@AuthenticationPrincipal UserDetails user){

        return ResponseEntity.ok(this.service.showUserProfileData(user));
    }

    @PostMapping("/profile/addFavMovie")
    public ResponseEntity<AddFavMovieResponseDto> addFavMovie(
        @AuthenticationPrincipal UserDetails user,
        @RequestBody SaveFavoriteMovieDto dto
    ){
        return ResponseEntity.ok(this.service.saveFavMovieIntoProfile(user, dto));
    }

    @GetMapping("/profile/myfavorites")
    public ResponseEntity<Page<MovieEntity>> findFavorites(
            @AuthenticationPrincipal UserDetails user,
            Pageable pageable
    ){
        return ResponseEntity.ok(this.service.listMyFavorites(user, pageable));
    }
}