package com.mercedes.starwarsbackend;

import com.mercedes.starwarsbackend.persistence.entity.People;
import com.mercedes.starwarsbackend.persistence.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PeopleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PeopleRepository peopleRepository;

    @BeforeEach
    void setup() {
        peopleRepository.deleteAll();
        peopleRepository.saveAll(createTestCharacters());
    }

    private List<People> createTestCharacters() {
        return List.of(
                // Luke Skywalker
                createCharacter("Luke Skywalker", "172", "77", "blond", "fair", "blue", "19BBY", "male",
                        "https://swapi.dev/api/planets/1/", List.of("https://swapi.dev/api/films/1/"), List.of(),
                        List.of("https://swapi.dev/api/vehicles/14/"), List.of("https://swapi.dev/api/starships/12/"),
                        "https://swapi.dev/api/people/1/"),

                // Princess Leia
                createCharacter("Leia Organa", "150", "49", "brown", "light", "brown", "19BBY", "female",
                        "https://swapi.dev/api/planets/2/", List.of("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/2/"), List.of(),
                        List.of("https://swapi.dev/api/vehicles/30/"), List.of(),
                        "https://swapi.dev/api/people/5/"),

                // Darth Vader
                createCharacter("Darth Vader", "202", "136", "none", "white", "yellow", "41.9BBY", "male",
                        "https://swapi.dev/api/planets/1/", List.of("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/2/", "https://swapi.dev/api/films/3/"), List.of(),
                        List.of(), List.of("https://swapi.dev/api/starships/13/"),
                        "https://swapi.dev/api/people/4/"),

                // Han Solo
                createCharacter("Han Solo", "180", "80", "brown", "fair", "brown", "29BBY", "male",
                        "https://swapi.dev/api/planets/22/", List.of("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/2/"), List.of(),
                        List.of(), List.of("https://swapi.dev/api/starships/10/"),
                        "https://swapi.dev/api/people/14/"),

                // Chewbacca
                createCharacter("Chewbacca", "228", "112", "brown", "unknown", "blue", "200BBY", "male",
                        "https://swapi.dev/api/planets/14/", List.of("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/2/"), List.of("https://swapi.dev/api/species/3/"),
                        List.of("https://swapi.dev/api/vehicles/19/"), List.of("https://swapi.dev/api/starships/10/"),
                        "https://swapi.dev/api/people/13/")
        );
    }

    private People createCharacter(String name, String height, String mass, String hairColor, String skinColor,
                                   String eyeColor, String birthYear, String gender, String homeworld,
                                   List<String> films, List<String> species, List<String> vehicles,
                                   List<String> starships, String url) {
        return People.builder()
                .name(name)
                .height(height)
                .mass(mass)
                .hairColor(hairColor)
                .skinColor(skinColor)
                .eyeColor(eyeColor)
                .birthYear(birthYear)
                .gender(gender)
                .homeworld(homeworld)
                .films(films)
                .species(species)
                .vehicles(vehicles)
                .starships(starships)
                .created(Instant.now())
                .edited(Instant.now())
                .url(url)
                .build();
    }

    @Test
    void shouldReturnAllCharactersInAlphabeticalOrder() throws Exception {
        mockMvc.perform(get("/api/v1/characters")
                        .param("page", "0")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.page.totalElements").value(5))
                .andExpect(jsonPath("$.page.totalPages").value(1))
                .andExpect(jsonPath("$.page.size").value(15))
                .andExpect(jsonPath("$.page.number").value(0))
                .andExpect(jsonPath("$.content[0].name").value("Chewbacca"))
                .andExpect(jsonPath("$.content[1].name").value("Darth Vader"))
                .andExpect(jsonPath("$.content[2].name").value("Han Solo"))
                .andExpect(jsonPath("$.content[3].name").value("Leia Organa"))
                .andExpect(jsonPath("$.content[4].name").value("Luke Skywalker"));
    }

    @Test
    void shouldFilterCharactersByNamePartialMatch() throws Exception {
        mockMvc.perform(get("/api/v1/characters")
                        .param("name", "luke")
                        .param("page", "0")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.page.totalElements").value(1))
                .andExpect(jsonPath("$.page.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"));
    }

    @Test
    void shouldReturnCharactersSortedByNameDescending() throws Exception {
        mockMvc.perform(get("/api/v1/characters")
                        .param("sort", "name,desc")
                        .param("page", "0")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.page.totalElements").value(5))
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.content[1].name").value("Leia Organa"))
                .andExpect(jsonPath("$.content[2].name").value("Han Solo"))
                .andExpect(jsonPath("$.content[3].name").value("Darth Vader"))
                .andExpect(jsonPath("$.content[4].name").value("Chewbacca"));
    }

}