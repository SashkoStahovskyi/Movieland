package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.dto.CurrencyType;

public interface CurrencyService {
    Movie convertCurrency(Movie movie, CurrencyType currencyType);

}

