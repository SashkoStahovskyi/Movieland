package com.stahovskyi.movieland.repository;

import com.stahovskyi.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findAllByIdIn(List<Integer> genresId);
}
