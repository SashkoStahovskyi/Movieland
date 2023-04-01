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
import com.stahovskyi.movieland.service.MovieService;
import com.stahovskyi.movieland.service.dto.request.MovieRequestDto;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import com.stahovskyi.movieland.service.entity.request.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stahovskyi.movieland.utils.RandomUtil.chooseRandom;
import static java.util.Objects.nonNull;
import static org.springframework.data.domain.Sort.Direction.fromString;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    private final static String RATING_PROPERTIES = "rating";
    private final static String PRICE_PROPERTIES = "price";
    private final MovieRepository movieRepository;
    private final CurrencyService currencyService;
    private final MovieMapper movieMapper;

    private final CountryRepository countryRepository;

    private final GenreRepository genreRepository;


    @Transactional
    @Override
    public Movie add(MovieRequestDto movieRequestDto) {
        List<Country> countries = countryRepository.findAllByIdIn(movieRequestDto.getCountry());
        List<Genre> genres = genreRepository.findAllByIdIn(movieRequestDto.getCountry());

        Movie movie = movieMapper.toMovie(movieRequestDto);
        movie.setCountries(countries);
        movie.setGenre(genres);

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAll(MovieRequest movieRequest) {

        if (nonNull(movieRequest.getRate()) && nonNull(movieRequest.getPrice())) {
            List<Order> ordersList = getOrders(movieRequest);
            return movieRepository.findAll(Sort.by(ordersList));
        }
        return nonNull(movieRequest.getPrice()) ?
                movieRepository.findAll(Sort.by(fromString(movieRequest.getPrice().getDirectional()), PRICE_PROPERTIES))
                : movieRepository.findAll(Sort.by(fromString(movieRequest.getRate().getDirectional()), RATING_PROPERTIES));
    }

    @Transactional(readOnly = true)
    @Override
    public Movie getById(int movieId, CurrencyType currencyType) {
        return movieRepository.findById(movieId)
                .map(movie -> currencyService.convertPrice(movie, currencyType))
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

    private List<Order> getOrders(MovieRequest movieRequest) {
        Direction ratingDirection = fromString(movieRequest.getRate().getDirectional());
        Direction priceDirection = fromString(movieRequest.getPrice().getDirectional());

        Order ratingOrder = new Order(ratingDirection, RATING_PROPERTIES);
        Order priceOrder = new Order(priceDirection, PRICE_PROPERTIES);

        return List.of(ratingOrder, priceOrder);
    }

}
