package com.stahovskyi.movieland.service.impl;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.stahovskyi.movieland.AbstractBaseITest;
import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.service.dto.CurrencyType;
import com.stahovskyi.movieland.web.controller.request.MovieRequest;
import com.stahovskyi.movieland.web.controller.request.SortDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DBRider
public class DefaultMovieServiceITest extends AbstractBaseITest {

    private final static int EXPECTED_MOVIE_BY_GENRE_COUNT = 2;
    private final static int EXPECTED_MOVIE_COUNT = 3;
    private final static int EXPECTED_MOVIE_ID = 6;
    private final static int GENRE_ID = 1;
    private final static int MOVIE_ID = 6;

    @Autowired
    private DefaultMovieService movieService;

    @Test
    @DBRider
    @DataSet("datasets/movie/movie_dataset.yml")
    @DisplayName("when Get All Sorted By Rating Param then All Sorted Movies Returned")
    void whenGetAllSortedByRating_thenAllSortedMoviesReturned() {
        // Given
        MovieRequest movieRequest = new MovieRequest(null, SortDirection.DESC);
        // When
        List<Movie> movieList = movieService.getAll(movieRequest);
        // Then
        assertAll(
                () -> assertThat(movieList.get(0).getRating()).isEqualTo(10.0),
                () -> assertThat(movieList.get(4).getRating()).isEqualTo(8.0),
                () -> assertThat(movieList.get(6).getRating()).isEqualTo(7.0),
                () -> assertThat(movieList.get(9).getRating()).isEqualTo(5.5)
        );
    }


    @Test
    @DBRider
    @DataSet("datasets/movie/movie_dataset.yml")
    @DisplayName("when Get All Sorted By Price Param then All Sorted Movies Returned")
    void whenGetAllSortedByPrice_thenAllSortedMoviesReturned() {
        // Given
        MovieRequest movieRequest = new MovieRequest(SortDirection.ASC, null);
        // When
        List<Movie> movieList = movieService.getAll(movieRequest);
        // Then
        assertAll(
                () -> assertThat(movieList.get(0).getPrice()).isEqualTo(100.5),
                () -> assertThat(movieList.get(4).getPrice()).isEqualTo(155.0),
                () -> assertThat(movieList.get(6).getPrice()).isEqualTo(170.5),
                () -> assertThat(movieList.get(9).getPrice()).isEqualTo(220.5)
        );
    }

    @Test
    @DBRider
    @DataSet(value = "datasets/movie/movie_dataset.yml", cleanBefore = true)
    @DisplayName("when Get Random Movie then Random Movies Returned")
    void whenGetRandomMovies_thenRandomMoviesReturned() {
        // When
        List<Movie> movieList = movieService.getRandom();
        // Then
        assertAll(
                () -> assertThat(movieList).isNotEmpty(),
                () -> assertThat(movieList).hasSize(EXPECTED_MOVIE_COUNT)
        );
    }


    @Test
    @DataSet("datasets/movie/movie_by_genre_id_dataset.yml")
    @DisplayName("when Get All Movie By Genre Id then Movie By Specified Genre Returned")
    void whenGetAllByGenreId_thenMovieBySpecifiedGenreReturned() {
        // When
        List<Movie> movieList = movieService.getAllByGenreId(GENRE_ID);
        // Then
        assertAll(
                () -> assertThat(movieList).isNotEmpty(),
                () -> assertThat(movieList).hasSize(EXPECTED_MOVIE_BY_GENRE_COUNT),
                () -> assertThat(movieList.get(0).getId()).isEqualTo(1),
                () -> assertThat(movieList.get(1).getId()).isEqualTo(2)
        );
    }

    @Test
    @DataSet(value = "datasets/movie/movie_dataset.yml")
    @DisplayName("when Get Movie By Id where Currency Type EUR then Movie With Converted Price Returned")
    void whenGetMovieById_whereCurrencyTypeEUR_thenMovieWithConvertedPriceReturned() {
        // When
        Movie movie = movieService.getById(MOVIE_ID, CurrencyType.EUR);
        // Then
        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(EXPECTED_MOVIE_ID);
    }

}