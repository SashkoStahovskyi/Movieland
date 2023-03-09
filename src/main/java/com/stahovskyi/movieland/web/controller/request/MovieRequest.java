package com.stahovskyi.movieland.web.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MovieRequest { // todo need refactoring

    String priceSortDirection;

    String ratingSortDirection;

}
