package com.mercedes.starwarsbackend.controller;

import com.mercedes.starwarsbackend.model.planet.PlanetDTO;
import com.mercedes.starwarsbackend.model.planet.PlanetFilterDTO;
import com.mercedes.starwarsbackend.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/planets")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping
    public ResponseEntity<Page<PlanetDTO>> getPlanets(
            PlanetFilterDTO filter,
            @PageableDefault(size = 15, sort = "name") Pageable pageable
    ) {
        var planetsPage = planetService.findPlanets(filter, pageable);
        return ResponseEntity.ok(planetsPage);
    }
}