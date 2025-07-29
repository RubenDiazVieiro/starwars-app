package com.mercedes.starwarsbackend.model.people;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercedes.starwarsbackend.persistence.entity.People;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PeopleDTO(
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

    public static PeopleDTO fromEntity(People people) {
        return new PeopleDTO(
                people.getName(),
                people.getHeight(),
                people.getMass(),
                people.getHairColor(),
                people.getSkinColor(),
                people.getEyeColor(),
                people.getBirthYear(),
                people.getGender(),
                people.getHomeworld(),
                people.getFilms(),
                people.getSpecies(),
                people.getVehicles(),
                people.getStarships(),
                people.getCreated(),
                people.getEdited(),
                people.getUrl()
        );
    }


}


