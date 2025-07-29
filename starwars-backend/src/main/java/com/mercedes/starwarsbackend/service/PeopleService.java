package com.mercedes.starwarsbackend.service;

import com.mercedes.starwarsbackend.model.people.PeopleDTO;
import com.mercedes.starwarsbackend.model.people.PeopleFilterDTO;
import com.mercedes.starwarsbackend.persistence.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public Page<PeopleDTO> findPeople(PeopleFilterDTO filter, Pageable pageable) {
        return peopleRepository.findWithFilters(filter.name(), pageable)
                .map(PeopleDTO::fromEntity);
    }

}