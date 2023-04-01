package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAllByIdIn(List<Integer> countriesId);
}
