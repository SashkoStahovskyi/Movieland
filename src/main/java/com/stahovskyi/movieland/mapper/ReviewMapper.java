package com.stahovskyi.movieland.mapper;

import com.stahovskyi.movieland.dto.ReviewDto;
import com.stahovskyi.movieland.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDto toReviewDto(Review review);
}
