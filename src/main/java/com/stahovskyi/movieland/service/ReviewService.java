package com.stahovskyi.movieland.service;

import com.stahovskyi.movieland.entity.Review;
import com.stahovskyi.movieland.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;


    public List<Review> findByMovieId(int movieId) {
        return reviewRepository.findByMovieId(movieId)
                .orElseThrow(() -> new RuntimeException("Review for movie not exist in DB! "));
    }

}
