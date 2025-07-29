package com.mercedes.starwarsbackend.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercedes.starwarsbackend.persistence.entity.People;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PeopleApiDTO(
        String name,
        String height,
        String mass,
        @JsonProperty("hair_color") String hairColor,
        @JsonProperty("skin_color") String skinColor,
        @JsonProperty("eye_color") String eyeColor,
        @JsonProperty("birth_year") String birthYear,
        String gender,
        String homeworld,
        List<String> films,
        List<String> species,
        List<String> vehicles,
        List<String> starships,
        Instant created,
        Instant edited,
        String url
) {

    public People toEntity() {
        return People.builder()
                .name(this.name())
                .height(this.height())
                .mass(this.mass())
                .hairColor(this.hairColor())
                .skinColor(this.skinColor())
                .eyeColor(this.eyeColor())
                .birthYear(this.birthYear())
                .gender(this.gender())
                .homeworld(this.homeworld())
                .films(this.films())
                .species(this.species())
                .vehicles(this.vehicles())
                .starships(this.starships())
                .created(this.created())
                .edited(this.edited())
                .url(this.url())
                .build();
    }

}


