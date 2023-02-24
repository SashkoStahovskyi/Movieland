package com.stahovskyi.movieland.web.controller;

import com.stahovskyi.movieland.cache.GenreCache;
import com.stahovskyi.movieland.dto.GenreDto;
import com.stahovskyi.movieland.mapper.Mappering;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/genre", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {
    private final GenreCache genreCache;
    private final Mappering mappering;


    @GetMapping
    public List<GenreDto> getAll() {
        return mappering.toGenreDtoList(genreCache.getAll());
    }

}