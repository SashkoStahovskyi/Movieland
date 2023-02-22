package com.stahovskyi.movieland.web.controller;

import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.mapper.Mappering;
import com.stahovskyi.movieland.service.MovieService;
import com.stahovskyi.movieland.web.controller.request.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;
    private final Mappering mapper;


    @GetMapping
    public List<MovieDto> getAll() {
        return mapper.toMovieDtoList(movieService.getAll());
    }

    @PostMapping
    public List<MovieDto> getAll(@RequestBody(required = false)
                                 MovieRequest request) {
        return mapper.toMovieDtoList(movieService.getAll(request));
    }

    @GetMapping(path = "/random")
    public List<MovieDto> getAllRandom() {
        return mapper.toMovieDtoList(movieService.getAllRandom());
    }

    @GetMapping(path = "/genre/{genreId}")
    public List<MovieDto> getAllByGenre(@PathVariable("genreId")
                                        int genreId) {
        return mapper.toMovieDtoList(movieService.getAllByGenre(genreId));
    }

}
