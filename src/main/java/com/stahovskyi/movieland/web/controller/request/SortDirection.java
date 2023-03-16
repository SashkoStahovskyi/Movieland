package com.stahovskyi.movieland.web.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum SortDirection {

    ASC("ASC"),

    DESC("DESC");

    @Getter
    private final String directional;

}
