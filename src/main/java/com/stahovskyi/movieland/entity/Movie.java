package com.stahovskyi.movieland.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_generator")
    @SequenceGenerator(name = "movie_id_generator", sequenceName = "movie_id_sequence")
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
