package com.jairomatheus.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "favorite",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "movie_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movieId;

}
