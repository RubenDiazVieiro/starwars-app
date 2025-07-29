package com.mercedes.starwarsbackend.service;

import com.mercedes.starwarsbackend.model.planet.PlanetDTO;
import com.mercedes.starwarsbackend.model.planet.PlanetFilterDTO;
import com.mercedes.starwarsbackend.persistence.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanetService {

    private final PlanetRepository planetRepository;

    public Page<PlanetDTO> findPlanets(PlanetFilterDTO filter, Pageable pageable) {
        return planetRepository.findWithFilters(filter.name(), pageable)
                .map(PlanetDTO::fromEntity);
    }

}