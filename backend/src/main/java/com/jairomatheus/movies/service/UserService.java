package com.jairomatheus.movies.service;

import com.jairomatheus.movies.dto.UserProfileDataDto;
import com.jairomatheus.movies.entity.UserEntity;
import com.jairomatheus.movies.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
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

}
