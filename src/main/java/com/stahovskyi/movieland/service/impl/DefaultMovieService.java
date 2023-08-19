package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Country;
import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.exception.NotFoundException;
import com.stahovskyi.movieland.mapper.MovieMapper;
import com.stahovskyi.movieland.repository.CountryRepository;
import com.stahovskyi.movieland.repository.GenreRepository;
import com.stahovskyi.movieland.repository.MovieRepository;
import com.stahovskyi.movieland.service.CurrencyService;
import com.stahovskyi.movieland.service.EnrichmentService;
import com.stahovskyi.movieland.service.MovieService;
import com.stahovskyi.movieland.service.dto.request.MovieRequestDto;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import com.stahovskyi.movieland.service.entity.request.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stahovskyi.movieland.utils.RandomUtil.chooseRandom;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.data.domain.Sort.Direction.fromString;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {
    private static final String RATING_PROPERTIES = "rating";
    private static final String PRICE_PROPERTIES = "price";
    private final CountryRepository countryRepository;
    private final EnrichmentService enrichmentService;
    private final MovieRepository movieRepository;
    private final CurrencyService currencyService;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;


    @Transactional
    @Override
    public Movie add(MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.toMovie(movieRequestDto);
        enrichMovieGenresAndCountries(movie, movieRequestDto);

        return movieRepository.save(movie);
    }

    @Transactional
    @Override
    public Movie update(int id, MovieRequestDto movieRequestDto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        enrichMovieGenresAndCountries(movie, movieRequestDto);
        movieMapper.update(movie, movieRequestDto);

        return movieRepository.save(movie);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAll(MovieRequest movieRequest) {

        if (isNull(movieRequest.getRating()) & isNull(movieRequest.getPrice())) {
            return movieRepository.findAll();
        }
        return nonNull(movieRequest.getPrice()) ?
                movieRepository.findAll(Sort.by(fromString(movieRequest.getPrice().getDirectional()), PRICE_PROPERTIES))
                : movieRepository.findAll(Sort.by(fromString(movieRequest.getRating().getDirectional()), RATING_PROPERTIES));
    }

    @Transactional(readOnly = true)
    @Override
    public Movie getById(int movieId, CurrencyType currencyType) {
        return movieRepository.findById(movieId)
                .map(movie -> currencyService.convertPrice(movie, currencyType))
                .map(enrichmentService::enrichMovie)
                .orElseThrow(() -> new NotFoundException(movieId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getRandom() {
        return chooseRandom(movieRepository.findAll());
    }


    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAllByGenreId(int genreId) {
        return movieRepository.getAllByGenreId(genreId)
                .orElseThrow(() -> new NotFoundException(genreId));
    }

    private void enrichMovieGenresAndCountries(Movie movie, MovieRequestDto movieRequestDto) {
        List<Country> countries = countryRepository.findAllByIdIn(movieRequestDto.getCountries());
        List<Genre> genres = genreRepository.findAllByIdIn(movieRequestDto.getGenres());
        movie.setCountries(countries);
        movie.setGenre(genres);
    }

}
