package com.stahovskyi.movieland.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CurrencyType {

    USD("USD"),

    EUR("EUR"),

    UAH("UAH");

    @Getter
    private final String type;

}
