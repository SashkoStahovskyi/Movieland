package com.stahovskyi.movieland.service.currency;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.CurrencyService;
import com.stahovskyi.movieland.service.currency.entity.Currency;
import com.stahovskyi.movieland.service.dto.CurrencyType;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

public class CurrencyConvertorService implements CurrencyService {

    private static final String NBU_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";

    private final WebClient nbuClient = WebClient.create(NBU_URI);


    @Override
    public Movie convertCurrency(Movie movie, CurrencyType currencyType) {

        if (currencyType.equals(CurrencyType.UAH)) {
            return movie;
        }
/*
        double actualRate = getExchangeRate(currencyType);

        double convertedPrice = convert(actualRate, movie.getPrice());

        movie.setPrice(convertedPrice);*/

        return movie;

    }

    private List<Currency> getExchangeRate(CurrencyType currencyType) {
       Mono<Currency[]> response = nbuClient.get()
               .accept(MediaType.APPLICATION_JSON)
               .retrieve()
               .bodyToMono(Currency[].class).log();
       Currency[] currencies = response.block();

        return List.of(Objects.requireNonNull(currencies));

    }
}

