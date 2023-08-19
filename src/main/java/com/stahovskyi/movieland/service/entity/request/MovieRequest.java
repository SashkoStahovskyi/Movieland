package com.stahovskyi.movieland.service.entity.request;

import com.stahovskyi.movieland.service.entity.common.PriceSortDirection;
import com.stahovskyi.movieland.service.entity.common.RatingSortDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    PriceSortDirection price;

    RatingSortDirection rating;

}
