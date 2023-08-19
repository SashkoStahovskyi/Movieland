package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Country;
import com.stahovskyi.movieland.repository.CountryRepository;
import com.stahovskyi.movieland.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultCountryService implements CountryService {

    private final CountryRepository countryRepository;


    @Override
    public List<Country> findAllByIdIn(List<Integer> countriesId) {
        return countryRepository.findAllByIdIn(countriesId);
    }

    @Override
    public List<Country> findByMovieId(int id) {
        return countryRepository.findByMovieId(id);
    }


}
