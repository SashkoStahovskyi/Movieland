package com.stahovskyi.movieland.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MovieDto {

    private int id;
    private String nameRussian;
    private String nameNative;
    private String description;
    private String picturePath;
    private LocalDate yearOfRelease;
    private int rating;
    private int price;
    private int votes;
}
