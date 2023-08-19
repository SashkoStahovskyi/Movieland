package com.stahovskyi.movieland.service.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum RatingSortDirection {

    DESC("DESC");

    @Getter
    private final String directional;

}
