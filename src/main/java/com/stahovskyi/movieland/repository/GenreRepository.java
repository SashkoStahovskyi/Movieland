package com.stahovskyi.movieland.repository;

import com.stahovskyi.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findAllByIdIn(List<Integer> genresId);

    @Query(value = "SELECT id, name FROM genre " +
            "INNER JOIN movie_genre " +
            "ON genre.id = movie_genre.genre_id " +
            "WHERE movie_genre.movie_id=?", nativeQuery = true)
    List<Genre> findAllByMovieId(int movieId);
}
