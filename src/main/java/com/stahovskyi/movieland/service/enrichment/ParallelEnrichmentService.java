package com.stahovskyi.movieland.service.enrichment;

import com.stahovskyi.movieland.entity.Country;
import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.entity.Review;
import com.stahovskyi.movieland.service.CountryService;
import com.stahovskyi.movieland.service.EnrichmentService;
import com.stahovskyi.movieland.service.GenreService;
import com.stahovskyi.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class ParallelEnrichmentService implements EnrichmentService {
    private final CountryService countryService;
    private final ReviewService reviewService;
    private final GenreService genreService;
    private final Executor executor;
    @Value("${task.timeOut.seconds}")
    private int timeTaskOut;


    @Override
    public Movie enrichMovie(Movie movie) {
        Future<List<Genre>> genres = findGenres(movie);
        Future<List<Country>> countries = findCountries(movie);
        Future<List<Review>> reviews = findReviews(movie);

        enrichMovieByGenre(movie, genres);
        enrichMovieByCountry(movie, countries);
        enrichMovieByReview(movie, reviews);
        return movie;
    }

    private void enrichMovieByReview(Movie movie, Future<List<Review>> futureReviews) {
        try {
            List<Review> reviews = futureReviews.get(timeTaskOut, TimeUnit.SECONDS);
            movie.setReviews(reviews);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.info("enrichment for genre interrupted with exception - {}!", e.getCause().getMessage());
            log.info("futureGenres is cancelled - {}!", futureReviews.isCancelled());
            Thread.currentThread().interrupt();
        }
    }

    private void enrichMovieByCountry(Movie movie, Future<List<Country>> futureCountries) {
        try {
            List<Country> countryList = futureCountries.get(timeTaskOut, TimeUnit.SECONDS);
            movie.setCountries(countryList);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.info("enrichment for genre interrupted with exception - {}!", e.getCause().getMessage());
            log.info("futureGenres is cancelled - {}!", futureCountries.isCancelled());
            Thread.currentThread().interrupt();
        }
    }

    private void enrichMovieByGenre(Movie movie, Future<List<Genre>> futureGenres) {
        try {
            List<Genre> genreList = futureGenres.get(timeTaskOut, TimeUnit.SECONDS);
            movie.setGenre(genreList);

        } catch (ExecutionException | TimeoutException | InterruptedException e) {
            log.info("enrichment for genre interrupted with exception - {}!", e.getCause().getMessage());
            log.info("futureGenres is cancelled - {}!", futureGenres.isCancelled());
            Thread.currentThread().interrupt();
        }
    }

    @Async
    Future<List<Genre>> findGenres(Movie movie) {
        Supplier<List<Genre>> genresTask = () -> {
            log.info("get genres form DB in parallel execution !");
            return genreService.findByMovieId(movie.getId());
        };
        return CompletableFuture.supplyAsync(genresTask, executor);
    }

    @Async
    Future<List<Country>> findCountries(Movie movie) {
        Supplier<List<Country>> getCountries = () -> {
            log.info(" Get countries in parallel !");
            return countryService.findByMovieId(movie.getId());
        };
        return CompletableFuture.supplyAsync(getCountries, executor);
    }

    @Async
    Future<List<Review>> findReviews(Movie movie) {
        Supplier<List<Review>> reviewTask = () -> {
            log.info(" Get reviews in parallel !");
            return reviewService.findByMovieId(movie.getId());
        };
        return CompletableFuture.supplyAsync(reviewTask, executor);
    }
}
