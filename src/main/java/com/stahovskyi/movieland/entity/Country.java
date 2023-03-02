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

@Setter
@Getter
@Entity
@Table(name = "COUNTRY")
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_generator")
    @SequenceGenerator(name = "country_id_generator", sequenceName = "country_id_sequence")
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String country;

}
