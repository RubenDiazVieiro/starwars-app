package com.mercedes.starwarsbackend.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercedes.starwarsbackend.persistence.entity.Planet;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlanetApiDTO(
        String name,
        @JsonProperty("rotation_period") String rotationPeriod,
        @JsonProperty("orbital_period") String orbitalPeriod,
        String diameter,
        String climate,
        String gravity,
        String terrain,
        @JsonProperty("surface_water") String surfaceWater,
        String population,
        List<String> residents,
        List<String> films,
        Instant created,
        Instant edited,
        String url
) {

    public Planet toEntity() {
        return Planet.builder()
                .name(this.name())
                .rotationPeriod(this.rotationPeriod())
                .orbitalPeriod(this.orbitalPeriod())
                .diameter(this.diameter())
                .climate(this.climate())
                .gravity(this.gravity())
                .terrain(this.terrain())
                .surfaceWater(this.surfaceWater())
                .population(this.population())
                .residents(this.residents())
                .films(this.films())
                .created(this.created())
                .edited(this.edited())
                .url(this.url())
                .build();
    }

}