package com.example.spacetravel.service;

import com.example.spacetravel.entity.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class PlanetCrudServiceTest {

    @Autowired
    private PlanetCrudService planetCrudService;

    @Test
    void shouldCreateReadUpdateAndDeletePlanet() {
        Planet created = planetCrudService.create("PLU", "Pluto");

        assertThat(created.getId()).isEqualTo("PLU");
        assertThat(planetCrudService.findById("PLU")).isPresent();

        Planet updated = planetCrudService.update("PLU", "Pluto Updated");
        assertThat(updated.getName()).isEqualTo("Pluto Updated");

        planetCrudService.delete("PLU");
        assertThat(planetCrudService.findById("PLU")).isEmpty();
    }

    @Test
    void shouldRejectInvalidPlanetId() {
        assertThatThrownBy(() -> planetCrudService.create("mars-lower", "Bad Mars"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
