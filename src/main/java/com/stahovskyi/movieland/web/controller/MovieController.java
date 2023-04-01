package com.stahovskyi.movieland.web.controller;

import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.mapper.MovieMapper;
import com.stahovskyi.movieland.service.MovieService;
import com.stahovskyi.movieland.service.dto.request.MovieRequestDto;
import com.stahovskyi.movieland.service.dto.response.DetailedMovieDto;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import com.stahovskyi.movieland.service.entity.request.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;


    @GetMapping
    protected List<MovieDto> getAll() {
        return movieMapper.toMovieDtoList(movieService.getAll());
    }

    @PostMapping
    protected List<MovieDto> getAll(@RequestBody(required = false)
                                    MovieRequest request) {
        return movieMapper.toMovieDtoList(movieService.getAll(request));

    }

    @GetMapping(path = "/{movieId}")
    protected DetailedMovieDto getById(@PathVariable(value = "movieId") int movieId,
                                       @RequestParam(value = "currency", required = false) CurrencyType currencyType) {
        return movieMapper.toDetailedMovieDto(movieService.getById(movieId, currencyType));

    }

    @GetMapping(path = "/random")
    protected List<MovieDto> getRandom() {
        return movieMapper.toMovieDtoList(movieService.getRandom());
    }

    @GetMapping(path = "/genre/{genreId}")
    protected List<MovieDto> getAllByGenreId(@PathVariable("genreId")
                                             int genreId) {
        return movieMapper.toMovieDtoList(movieService.getAllByGenreId(genreId));
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    protected MovieDto add(@RequestBody MovieRequestDto movieRequestDto) {
        return movieMapper.toMovieDto(movieService.add(movieRequestDto));

    }

}
