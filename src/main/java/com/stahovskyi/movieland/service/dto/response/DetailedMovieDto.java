package com.stahovskyi.movieland.service.dto.response;

import com.stahovskyi.movieland.dto.CountryDto;
import com.stahovskyi.movieland.dto.GenreDto;
import com.stahovskyi.movieland.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedMovieDto {

    private int id;

    private String nameRussian;

    private String nameNative;

    private String description;

    private String picturePath;

    private int yearOfRelease;

    private double rating;

    private double price;

    private int votes;

    private List<GenreDto> genres;

    private List<CountryDto> countries;

    private List<ReviewDto> reviews;

}
