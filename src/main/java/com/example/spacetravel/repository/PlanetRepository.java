package com.example.spacetravel.repository;

import com.example.spacetravel.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, String> {
}
