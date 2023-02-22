package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.repository.MovieRepository;
import com.stahovskyi.movieland.service.MovieService;
import com.stahovskyi.movieland.web.controller.request.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final static String RATING_PROPERTIES = "rating";
    private final static String PRICE_PROPERTIES = "price";
    private final static int RANDOM_MOVIE_NUMBER = 3;


    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getAll(MovieRequest movieRequest) {

        if (nonNull(movieRequest.getRatingSortDirection()) || nonNull(movieRequest.getPriceSortDirection())) {
            List<Order> ordersList = getOrders(movieRequest);

            return movieRepository.findAll(Sort.by(ordersList));
        }

        return nonNull(movieRequest.getPriceSortDirection()) ?
                movieRepository.findAll(Sort.by(Direction.fromString(movieRequest.getPriceSortDirection()), PRICE_PROPERTIES))
                : movieRepository.findAll(Sort.by(Direction.fromString(movieRequest.getRatingSortDirection()), RATING_PROPERTIES));

    }


    @Override
    public List<Movie> getAllRandom() {
        List<Movie> movieList = movieRepository.findAll();
        List<Movie> result = new ArrayList<>();

        for (int i = 0; i < RANDOM_MOVIE_NUMBER; i++) {
            result.add(movieList.get(new Random().nextInt(1, movieList.size())));
        }
        return result;
    }

    @Override
    public List<Movie> getAllByGenre(int genreId) {
        return movieRepository.getAllByGenreId(genreId);
    }

    private List<Order> getOrders(MovieRequest movieRequest) {
        Direction ratingDirection = Direction.fromString(movieRequest.getRatingSortDirection());
        Direction priceDirection = Direction.fromString(movieRequest.getPriceSortDirection());

        Order ratingOrder = new Order(ratingDirection, RATING_PROPERTIES);
        Order priceOrder = new Order(priceDirection, PRICE_PROPERTIES);

        return List.of(ratingOrder, priceOrder);
    }

}
