package com.stahovskyi.movieland.repository;

import com.stahovskyi.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<List<Movie>> getAllByGenreId(int genreId);


    @Query(value = "select m from Movie m where m.nameNative = :name")
    Optional<Movie> findByMovieName(@Param("name") String name);

}
