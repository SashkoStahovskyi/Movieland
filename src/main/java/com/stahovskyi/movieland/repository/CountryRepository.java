package com.stahovskyi.movieland.repository;

import com.stahovskyi.movieland.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAllByIdIn(List<Integer> countriesId);

    @Query(value = "SELECT country.id, country.name " +
            "FROM movie_country " +
            "JOIN country  " +
            "ON movie_country.country_id = country.id  " +
            "WHERE movie_id = :movieId", nativeQuery = true)
    List<Country> findByMovieId(@Param("movieId") Integer movieId);

}
