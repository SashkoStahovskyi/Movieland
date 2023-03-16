package com.stahovskyi.movieland.web.controller;

import com.stahovskyi.movieland.dto.GenreDto;
import com.stahovskyi.movieland.mapper.GenreMapper;
import com.stahovskyi.movieland.service.GenreService;
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
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping
    protected List<GenreDto> getAll() {
        return genreMapper.toGenreDtoList(genreService.getAll());
    }

}
