package com.stahovskyi.movieland.repository;

import com.stahovskyi.movieland.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAllByIdIn(List<Integer> countriesId);

}
