package com.mercedes.starwarsbackend.client;

import com.mercedes.starwarsbackend.client.model.SwapiPagedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SwapiDataLoader {

    private final RestClient restClient;

    public <T> List<T> loadAllData(String endpoint, ParameterizedTypeReference<SwapiPagedResponse<T>> responseType) {
        List<T> allResults = new ArrayList<>();
        int currentPage = 1;
        boolean hasMorePages = true;

        while (hasMorePages) {
            SwapiPagedResponse<T> response = fetchPage(endpoint, currentPage, responseType);

            if (response == null || response.results().isEmpty()) {
                hasMorePages = false;
            } else {
                allResults.addAll(response.results());
                hasMorePages = response.next() != null;
                currentPage++;
            }
        }

        return allResults;
    }

    private <T> SwapiPagedResponse<T> fetchPage(
            String endpoint,
            int page,
            ParameterizedTypeReference<SwapiPagedResponse<T>> responseType) {

        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(endpoint)
                            .queryParam("page", page)
                            .build())
                    .retrieve()
                    .body(responseType);
        } catch (Exception e) {
            log.error("Error fetching page {} from endpoint {}: {}", page, endpoint, e.getMessage());
            return null;
        }
    }

}