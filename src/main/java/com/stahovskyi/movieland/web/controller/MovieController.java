package com.stahovskyi.movieland.web.controller;

import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.mapper.MovieMapper;
import com.stahovskyi.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;


    @GetMapping
    public List<MovieDto> getAll() {
        return movieService.getAll().stream()
                .map(movieMapper)
                .collect(Collectors.toList());
    }


    @GetMapping(path = "/random")
    public List<MovieDto> getRandom() {
        return movieService.getRandom().stream()
                .map(movieMapper)
                .collect(Collectors.toList());
    }

}
