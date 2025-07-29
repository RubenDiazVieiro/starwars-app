package com.mercedes.starwarsbackend.client;

import com.mercedes.starwarsbackend.client.model.PlanetApiDTO;
import com.mercedes.starwarsbackend.client.model.SwapiPagedResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanetClient {

    private final SwapiDataLoader dataLoader;
    private final ParameterizedTypeReference<SwapiPagedResponse<PlanetApiDTO>> planetListType = new ParameterizedTypeReference<>() {};

    public PlanetClient(SwapiDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public List<PlanetApiDTO> getAll() {
        return dataLoader.loadAllData("/planets", planetListType);
    }

}
