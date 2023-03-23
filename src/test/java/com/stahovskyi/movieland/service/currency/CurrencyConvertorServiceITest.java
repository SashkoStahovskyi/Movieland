package com.stahovskyi.movieland.service.currency;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.entity.common.Currency;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import com.stahovskyi.movieland.service.impl.CurrencyConvertorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CurrencyConvertorServiceITest {

    private final static int EXPECTED_NUMBER_OF_CURRENCIES = 61;
    private final static double MOVIE_PRICE = 150.0;
    private CurrencyConvertorService currencyService;
    private List<Currency> currencyList;


    @BeforeAll
    public void setUp() {
        currencyService = new CurrencyConvertorService();
        currencyList = currencyService.getCurrentExchangeRate();
    }

    @Test
    @DisplayName("when Convert Price where Provided Currency Type EUR then Movie With Converted Price Returned")
    void whenConvertPrice_whereProvidedCurrencyTypeEUR_thenMovieWithConvertedPriceReturned() {
        // Given
        Movie movie = Movie.builder()
                .price(MOVIE_PRICE)
                .build();
        // When
        Movie actualMovie = currencyService.convertPrice(movie, CurrencyType.EUR);
        // Then
        assertThat(actualMovie.getPrice())
                .isNotNull()
                .isNotEqualTo(MOVIE_PRICE);
    }

    @Test
    @DisplayName("when Get Current Exchange Rate then Appropriate Exchange Rate List Returned")
    void whenGetCurrentExchangeRate_thenAppropriateExchangeRateListReturned() {

        assertThat(currencyList)
                .isNotEmpty()
                .hasSize(EXPECTED_NUMBER_OF_CURRENCIES);

        assertAll(
                () -> assertThat(currencyList.get(0).getSerialNumber()).isEqualTo(36),
                () -> assertThat(currencyList.get(0).getCurrencyName()).isEqualTo("Австралійський долар"),
                () -> assertThat(currencyList.get(0).getCurrencyType()).isEqualTo("AUD")
        );
    }

}