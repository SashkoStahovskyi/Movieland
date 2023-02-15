package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.dto.MovieDto;
import com.stahovskyi.movieland.entity.Movie;
import com.stahovskyi.movieland.repository.MovieRepository;
import com.stahovskyi.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;


    public List<Movie> getAll() {
       return movieRepository.findAll();
   }

}
