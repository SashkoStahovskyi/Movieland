package com.stahovskyi.movieland.service.entity.request;

import com.stahovskyi.movieland.service.entity.common.SortDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MovieRequest {

    SortDirection price;

    SortDirection rate;

}
