package com.stahovskyi.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "MOVIE")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_generator")
    @SequenceGenerator(name = "movie_id_generator", sequenceName = "movie_id_sequence")
    @Column(name = "id")
    private int id;

    @Column(name = "name_russian")
    private String nameRussian;

    @Column(name = "name_native")
    private String nameNative;

    @Column(name = "description")
    private String description;

    @Column(name = "picture_path")
    private String picturePath;

    @Column(name = "year_of_release")
    private LocalDate yearOfRelease;

    @Column(name = "rating")
    private int rating;        // todo change by double

    @Column(name = "price")
    private int price;

    @Column(name = "votes")
    private int votes;

    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private List<Genre> genre = new ArrayList<>();  // todo check why ?

}
