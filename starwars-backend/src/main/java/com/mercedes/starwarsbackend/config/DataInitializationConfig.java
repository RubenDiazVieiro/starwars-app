package com.mercedes.starwarsbackend.config;

import com.mercedes.starwarsbackend.client.PeopleClient;
import com.mercedes.starwarsbackend.client.PlanetClient;
import com.mercedes.starwarsbackend.client.model.PeopleApiDTO;
import com.mercedes.starwarsbackend.client.model.PlanetApiDTO;
import com.mercedes.starwarsbackend.persistence.repository.PeopleRepository;
import com.mercedes.starwarsbackend.persistence.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializationConfig {

    private final PlanetClient planetClient;
    private final PlanetRepository planetRepository;
    private final PeopleClient peopleClient;
    private final PeopleRepository peopleRepository;

    @Bean
    public CommandLineRunner initializeStarWarsData() {
        return args -> {
            log.info("🚀 Starting to save Star Wars data from SWAPI...");
            
            loadPlanetsData();
            loadPeopleData();
            
            log.info("✅ Data initialization completed");
        };
    }

    private void loadPlanetsData() {
        var planets = planetClient.getAll();

        planetRepository.saveAll(
                planets.stream()
                        .map(PlanetApiDTO::toEntity)
                        .toList()
        );
    }

    private void loadPeopleData() {
        var people = peopleClient.getAll();

        peopleRepository.saveAll(
                people.stream()
                        .map(PeopleApiDTO::toEntity)
                        .toList()
        );
    }

}