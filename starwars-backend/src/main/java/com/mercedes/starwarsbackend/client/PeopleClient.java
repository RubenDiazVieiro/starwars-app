package com.mercedes.starwarsbackend.client;

import com.mercedes.starwarsbackend.client.model.PeopleApiDTO;
import com.mercedes.starwarsbackend.client.model.SwapiPagedResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleClient {

    private final SwapiDataLoader dataLoader;
    private final ParameterizedTypeReference<SwapiPagedResponse<PeopleApiDTO>> peopleListType = new ParameterizedTypeReference<>() {};

    public PeopleClient(SwapiDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public List<PeopleApiDTO> getAll() {
        return dataLoader.loadAllData("/people", peopleListType);
    }

}
