package com.mercedes.starwarsbackend.controller;

import com.mercedes.starwarsbackend.model.people.PeopleDTO;
import com.mercedes.starwarsbackend.model.people.PeopleFilterDTO;
import com.mercedes.starwarsbackend.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/characters")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping
    public ResponseEntity<Page<PeopleDTO>> getPeople(
            PeopleFilterDTO filter,
            @PageableDefault(size = 15, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        var planetsPage = peopleService.findPeople(filter, pageable);
        return ResponseEntity.ok(planetsPage);
    }
}