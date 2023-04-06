package com.stahovskyi.movieland.service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {


    private String nameRussian;

    private String nameNative;

    private int yearOfRelease;

    private String description;

    private double price;

    private String picturePath;

    private List<Integer> countries;

    private List<Integer> genres;

}
