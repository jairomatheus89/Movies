package com.jairomatheus.movies.repositories;

import com.jairomatheus.movies.entity.FavoriteEntity;
import com.jairomatheus.movies.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    Optional<FavoriteEntity> findByUserId(Long userId);

}
