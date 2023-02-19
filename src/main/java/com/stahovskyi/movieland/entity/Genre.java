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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "GENRE")
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id_generator")
    @SequenceGenerator(name = "genre_id_generator", sequenceName = "genre_id_sequence")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

}
