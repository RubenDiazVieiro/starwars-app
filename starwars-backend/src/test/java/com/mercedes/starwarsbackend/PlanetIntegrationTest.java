package com.mercedes.starwarsbackend;

import com.mercedes.starwarsbackend.persistence.entity.Planet;
import com.mercedes.starwarsbackend.persistence.repository.PlanetRepository;
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
class PlanetIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlanetRepository planetRepository;

    @BeforeEach
    void setup() {
        planetRepository.deleteAll();
        planetRepository.saveAll(createTestPlanets());
    }

    private List<Planet> createTestPlanets() {
        return List.of(
                // Tatooine
                createPlanet("Tatooine", "23", "304", "10465", "arid", "1 standard", "desert",
                        "1", "200000", List.of("https://swapi.dev/api/people/1/", "https://swapi.dev/api/people/11/"),
                        List.of("https://swapi.dev/api/films/1/"), "https://swapi.dev/api/planets/1/"),

                // Alderaan
                createPlanet("Alderaan", "24", "364", "12500", "temperate", "1 standard", "grasslands, mountains",
                        "40", "2000000000", List.of("https://swapi.dev/api/people/5/", "https://swapi.dev/api/people/68/"),
                        List.of("https://swapi.dev/api/films/1/"), "https://swapi.dev/api/planets/2/"),

                // Yavin IV
                createPlanet("Yavin IV", "24", "4818", "10200", "temperate, tropical", "1 standard", "jungle, rainforests",
                        "8", "1000", List.of(),
                        List.of("https://swapi.dev/api/films/1/"), "https://swapi.dev/api/planets/3/"),

                // Hoth
                createPlanet("Hoth", "23", "549", "7200", "frozen", "1.1 standard", "tundra, ice caves, mountain ranges",
                        "100", "unknown", List.of(),
                        List.of("https://swapi.dev/api/films/2/"), "https://swapi.dev/api/planets/4/"),

                // Dagobah
                createPlanet("Dagobah", "23", "341", "8900", "murky", "N/A", "swamp, jungles",
                        "8", "unknown", List.of(),
                        List.of("https://swapi.dev/api/films/2/", "https://swapi.dev/api/films/3/"), "https://swapi.dev/api/planets/5/")
        );
    }

    private Planet createPlanet(String name, String rotationPeriod, String orbitalPeriod, String diameter,
                               String climate, String gravity, String terrain, String surfaceWater,
                               String population, List<String> residents, List<String> films, String url) {
        return Planet.builder()
                .name(name)
                .rotationPeriod(rotationPeriod)
                .orbitalPeriod(orbitalPeriod)
                .diameter(diameter)
                .climate(climate)
                .gravity(gravity)
                .terrain(terrain)
                .surfaceWater(surfaceWater)
                .population(population)
                .residents(residents)
                .films(films)
                .created(Instant.now())
                .edited(Instant.now())
                .url(url)
                .build();
    }

    @Test
    void shouldReturnAllPlanetsInAlphabeticalOrder() throws Exception {
        mockMvc.perform(get("/api/v1/planets")
                        .param("page", "0")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.page.totalElements").value(5))
                .andExpect(jsonPath("$.page.totalPages").value(1))
                .andExpect(jsonPath("$.page.size").value(15))
                .andExpect(jsonPath("$.page.number").value(0))
                .andExpect(jsonPath("$.content[0].name").value("Alderaan"))
                .andExpect(jsonPath("$.content[1].name").value("Dagobah"))
                .andExpect(jsonPath("$.content[2].name").value("Hoth"))
                .andExpect(jsonPath("$.content[3].name").value("Tatooine"))
                .andExpect(jsonPath("$.content[4].name").value("Yavin IV"));
    }

    @Test
    void shouldFilterPlanetsByNamePartialMatch() throws Exception {
        mockMvc.perform(get("/api/v1/planets")
                        .param("name", "tatooine")
                        .param("page", "0")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.page.totalElements").value(1))
                .andExpect(jsonPath("$.page.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Tatooine"));
    }

    @Test
    void shouldReturnPlanetsSortedByNameDescending() throws Exception {
        mockMvc.perform(get("/api/v1/planets")
                        .param("sort", "name,desc")
                        .param("page", "0")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(5))
                .andExpect(jsonPath("$.page.totalElements").value(5))
                .andExpect(jsonPath("$.content[0].name").value("Yavin IV"))
                .andExpect(jsonPath("$.content[1].name").value("Tatooine"))
                .andExpect(jsonPath("$.content[2].name").value("Hoth"))
                .andExpect(jsonPath("$.content[3].name").value("Dagobah"))
                .andExpect(jsonPath("$.content[4].name").value("Alderaan"));
    }

}