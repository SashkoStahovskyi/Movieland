package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.repository.MovieGenreRepository;
import com.stahovskyi.movieland.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieGenreServiceImpl implements MovieGenreService {

    // todo -> why failed with constant ??
    /*private static final Sort SORT_BY_ASC_DIRECTION = Sort.by(Sort.Direction.ASC);*/
    private final MovieGenreRepository movieGenreRepository;

    @Override
    public List<Genre> getAllGenre() {
        return movieGenreRepository.findAll();
    }
}
