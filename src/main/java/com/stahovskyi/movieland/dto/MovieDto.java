package com.stahovskyi.movieland.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private int id;

    private String nameRussian;

    private String nameNative;

    private String description;

    private String picturePath;

    private int yearOfRelease;

    private double rating;

    private double price;

    private int votes;

}
