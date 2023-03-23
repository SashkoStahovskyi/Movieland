package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;

public interface CurrencyService {
    Movie convertPrice(Movie movie, CurrencyType currencyType);

}

