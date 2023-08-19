package com.stahovskyi.movieland.service.impl;

import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.repository.GenreRepository;
import com.stahovskyi.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;


    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "genre_cache")
    @Override
    public List<Genre> getAll() {
        log.info(" Update genres in cache ! ");
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findAllByIdIn(List<Integer> genresId) {
        return genreRepository.findAllByIdIn(genresId);
    }

    @Override
    public List<Genre> findByMovieId(int movieId) {
        return genreRepository.findAllByMovieId(movieId);
    }

    @Scheduled(initialDelayString = "${scheduled.initialDelay.hours}", fixedDelayString = "${scheduled.fixedDelay.hours}",
            timeUnit = TimeUnit.HOURS)
    @CacheEvict(cacheNames = "genre_cache", allEntries = true)
    public void genreCacheEvict() {
        log.info(" The cache of genres has been removed ! ");

    }

}
