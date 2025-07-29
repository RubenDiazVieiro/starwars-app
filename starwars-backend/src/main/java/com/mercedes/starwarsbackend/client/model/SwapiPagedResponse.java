package com.mercedes.starwarsbackend.client.model;

import java.util.List;

public record SwapiPagedResponse<T>(
        int count,
        String next,
        String previous,
        List<T> results
) {}
