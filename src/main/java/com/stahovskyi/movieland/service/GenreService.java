package com.stahovskyi.movieland.service;


import com.stahovskyi.movieland.entity.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    List<Genre> findAllByIdIn(List<Integer> genresId);

}
