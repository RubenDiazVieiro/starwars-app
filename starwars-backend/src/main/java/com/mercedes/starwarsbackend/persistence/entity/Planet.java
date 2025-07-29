package com.mercedes.starwarsbackend.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "planets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "rotation_period")
    private String rotationPeriod;

    @Column(name = "orbital_period")
    private String orbitalPeriod;

    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;

    @Column(name = "surface_water")
    private String surfaceWater;

    private String population;

    @ElementCollection
    @CollectionTable(name = "planet_residents", joinColumns = @JoinColumn(name = "planet_id"))
    @Column(name = "resident_url")
    private List<String> residents;

    @ElementCollection
    @CollectionTable(name = "planet_films", joinColumns = @JoinColumn(name = "planet_id"))
    @Column(name = "film_url")
    private List<String> films;

    private Instant created;
    private Instant edited;
    
    @Column(name = "swapi_url", unique = true)
    private String url;
}