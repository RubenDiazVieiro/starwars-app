package com.mercedes.starwarsbackend.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "people")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String height;
    private String mass;

    @Column(name = "hair_color")
    private String hairColor;

    @Column(name = "skin_color")
    private String skinColor;

    @Column(name = "eye_color")
    private String eyeColor;

    @Column(name = "birth_year")
    private String birthYear;

    private String gender;
    private String homeworld;

    @ElementCollection
    @CollectionTable(name = "people_films", joinColumns = @JoinColumn(name = "people_id"))
    @Column(name = "film_url")
    private List<String> films;

    @ElementCollection
    @CollectionTable(name = "people_species", joinColumns = @JoinColumn(name = "people_id"))
    @Column(name = "species_url")
    private List<String> species;

    @ElementCollection
    @CollectionTable(name = "people_vehicles", joinColumns = @JoinColumn(name = "people_id"))
    @Column(name = "vehicle_url")
    private List<String> vehicles;

    @ElementCollection
    @CollectionTable(name = "people_starships", joinColumns = @JoinColumn(name = "people_id"))
    @Column(name = "starship_url")
    private List<String> starships;

    private Instant created;
    private Instant edited;
    
    @Column(name = "swapi_url", unique = true)
    private String url;
}