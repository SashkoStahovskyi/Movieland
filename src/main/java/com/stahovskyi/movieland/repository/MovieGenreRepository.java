package com.stahovskyi.movieland.repository;

import com.stahovskyi.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<Genre, Integer> {

}
