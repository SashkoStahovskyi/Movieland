package com.stahovskyi.movieland.cache;

import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenreCache implements CacheService {
    private final Map<String, List<Genre>> genreMap = new ConcurrentHashMap<>();
    private final CacheTimerTask timerTask = new CacheTimerTask();
    private final GenreRepository genreRepository;
    private final Timer timer = new Timer();
    private static final long REPETITION_PERIOD = 14_400_000;
    private static final String GENRES_KEY = "genres";
    private static final int DELAY = 0;


    {
        timer.scheduleAtFixedRate(timerTask, DELAY, REPETITION_PERIOD);
    }


    @Override
    public List<Genre> getAllGenres() {
        return new ArrayList<>(genreMap.get(GENRES_KEY));

    }

    private void updateCache() {
        List<Genre> genreList = genreRepository.findAll();
        genreMap.merge(GENRES_KEY, genreList, ((oldGenreList, updatedGenreList) -> updatedGenreList));
    }

    private class CacheTimerTask extends TimerTask {

        @Override
        public void run() {
            updateCache();
            log.info("Update genres in cache! Get {} item!", genreMap.get(GENRES_KEY).size());
        }
    }

}

