package com.mercedes.starwarsbackend.persistence.repository;

import com.mercedes.starwarsbackend.persistence.entity.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    @Query("SELECT p FROM Planet p WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<Planet> findWithFilters(@Param("name") String name, Pageable pageable);


}