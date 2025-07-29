package com.mercedes.starwarsbackend.model.planet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercedes.starwarsbackend.persistence.entity.Planet;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlanetDTO(
        Long id,
        String name,
        @JsonProperty("rotation_period") String rotationPeriod,
        @JsonProperty("orbital_period") String orbitalPeriod,
        String diameter,
        String climate,
        String gravity,
        String terrain,
        @JsonProperty("surface_water") String surfaceWater,
        String population,
        @JsonProperty("residents_count") Integer residentsCount,
        @JsonProperty("films_count") Integer filmsCount,
        Instant created
) {

    public static PlanetDTO fromEntity(Planet planet) {
        return new PlanetDTO(
                planet.getId(),
                planet.getName(),
                planet.getRotationPeriod(),
                planet.getOrbitalPeriod(),
                planet.getDiameter(),
                planet.getClimate(),
                planet.getGravity(),
                planet.getTerrain(),
                planet.getSurfaceWater(),
                planet.getPopulation(),
                planet.getResidents() != null ? planet.getResidents().size() : 0,
                planet.getFilms() != null ? planet.getFilms().size() : 0,
                planet.getCreated()
        );
    }
}