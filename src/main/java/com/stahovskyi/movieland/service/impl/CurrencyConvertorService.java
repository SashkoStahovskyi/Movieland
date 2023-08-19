package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.CurrencyService;
import com.stahovskyi.movieland.service.entity.common.Currency;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class CurrencyConvertorService implements CurrencyService {

    private static final String NBU_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";
    private final WebClient nbuClient = WebClient.create(NBU_URI);
    private static final int SCALE = 2;

    @Override
    public Movie convertPrice(Movie movie, CurrencyType currencyType) {
        if (currencyType != CurrencyType.UAH) {
            List<Currency> currenciesList = getCurrentExchangeRate();

            double rate = getRate(currenciesList, currencyType);
            double price = convert(rate, movie.getPrice());

            movie.setPrice(price);
            return movie;
        }
        return movie;
    }

    public List<Currency> getCurrentExchangeRate() { // todo retry implement
        Mono<Currency[]> response = nbuClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Currency[].class).log();

        Currency[] currencies = response.block();
        return List.of(ofNullable(currencies).orElseThrow());
    }

    private double getRate(List<Currency> currencies, CurrencyType currencyType) {
        return currencies.stream()
                .filter(currency -> currency.getCurrencyType().equals(currencyType.getType()))
                .map(Currency::getRate)
                .findFirst()
                .orElseThrow();
    }

    private double convert(double rate, double moviePrice) {
        return BigDecimal.valueOf(moviePrice / rate)
                .setScale(SCALE, RoundingMode.CEILING)
                .doubleValue();
    }

}

