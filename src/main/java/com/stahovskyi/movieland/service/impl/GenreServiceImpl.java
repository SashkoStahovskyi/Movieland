package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.repository.GenreRepository;
import com.stahovskyi.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    // todo -> why failed with constant ??
    /*private static final Sort SORT_BY_ASC_DIRECTION = Sort.by(Sort.Direction.ASC);*/
    private final GenreRepository movieGenreRepository;

    @Override
    public List<Genre> getAll() {
        return movieGenreRepository.findAll();
      /*  userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));*/
    }
}
