package com.example.spacetravel.service;

import com.example.spacetravel.entity.Planet;
import com.example.spacetravel.repository.PlanetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlanetCrudService {

    private final PlanetRepository planetRepository;

    public PlanetCrudService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    public Optional<Planet> findById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return planetRepository.findById(id.trim().toUpperCase());
    }

    @Transactional
    public Planet create(String id, String name) {
        validateId(id);
        validateName(name);
        String normalizedId = id.trim().toUpperCase();
        if (planetRepository.existsById(normalizedId)) {
            throw new IllegalArgumentException("Planet already exists: " + normalizedId);
        }
        return planetRepository.save(new Planet(normalizedId, name.trim()));
    }

    @Transactional
    public Planet update(String id, String name) {
        validateId(id);
        validateName(name);
        String normalizedId = id.trim().toUpperCase();
        Planet planet = planetRepository.findById(normalizedId)
                .orElseThrow(() -> new IllegalArgumentException("Planet not found: " + normalizedId));
        planet.setName(name.trim());
        return planetRepository.save(planet);
    }

    @Transactional
    public void delete(String id) {
        validateId(id);
        String normalizedId = id.trim().toUpperCase();
        if (!planetRepository.existsById(normalizedId)) {
            throw new IllegalArgumentException("Planet not found: " + normalizedId);
        }
        planetRepository.deleteById(normalizedId);
    }

    private void validateId(String id) {
        if (id == null || id.isBlank() || !id.trim().matches("^[A-Z0-9]+$")) {
            throw new IllegalArgumentException("Planet id must contain only uppercase latin letters and digits");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.trim().length() > 500) {
            throw new IllegalArgumentException("Planet name must contain from 1 to 500 characters");
        }
    }
}
