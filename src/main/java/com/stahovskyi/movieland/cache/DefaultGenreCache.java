package com.stahovskyi.movieland.cache;

import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultGenreCache implements CacheService {

    private final Map<String, List<Genre>> genreMap = new ConcurrentHashMap<>();
    private final GenreRepository genreRepository;
    private static final String GENRES_KEY = "genres";


    @Override
    public List<Genre> getAllGenres() {
        return new ArrayList<>(genreMap.get(GENRES_KEY));
    }

    @PostConstruct
    @Scheduled(initialDelayString = "${scheduled.initialDelay.hours}", fixedDelayString = "${scheduled.fixedDelay.hours}",
            timeUnit = TimeUnit.HOURS)
    private void update() {
        genreMap.put(GENRES_KEY, genreRepository.findAll());
        log.info("Update genres in cache! Get {} item!", genreMap.get(GENRES_KEY).size());

    }


}
