package com.stahovskyi.movieland.repository;

import com.stahovskyi.movieland.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @EntityGraph(attributePaths = {"user"})
    Optional<List<Review>> findByMovieId(int movieId);
}
