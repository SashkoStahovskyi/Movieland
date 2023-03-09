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

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultGenreServiceImpl implements GenreService {

    private final GenreRepository movieGenreRepository;


    @Cacheable(cacheNames = "genre_cache")
    @Override
    public List<Genre> getAll() {
        log.info(" Update genres in cache ! ");
        return movieGenreRepository.findAll();

    }

    @Scheduled(initialDelayString = "${initialDelay.in.hours}", fixedDelayString = "${fixedDelay.in.hours}",
            timeUnit = TimeUnit.HOURS)
    @CacheEvict(cacheNames = "genre_cache", allEntries = true)
    public void genreCacheEvict() {
        log.info(" The cache of genres has been removed ! ");

    }

}
