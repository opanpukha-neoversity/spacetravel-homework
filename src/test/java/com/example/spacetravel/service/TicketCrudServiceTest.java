package com.example.spacetravel.service;

import com.example.spacetravel.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class TicketCrudServiceTest {

    @Autowired
    private TicketCrudService ticketCrudService;

    @Test
    void shouldCreateReadUpdateAndDeleteTicket() {
        Ticket created = ticketCrudService.create(1L, "EARTH", "MARS");

        assertThat(created.getId()).isNotNull();
        assertThat(created.getCreatedAt()).isNotNull();
        assertThat(created.getClient().getId()).isEqualTo(1L);
        assertThat(created.getFromPlanet().getId()).isEqualTo("EARTH");
        assertThat(created.getToPlanet().getId()).isEqualTo("MARS");

        Ticket updated = ticketCrudService.update(created.getId(), 2L, "MARS", "VEN");
        assertThat(updated.getClient().getId()).isEqualTo(2L);
        assertThat(updated.getFromPlanet().getId()).isEqualTo("MARS");
        assertThat(updated.getToPlanet().getId()).isEqualTo("VEN");

        ticketCrudService.delete(created.getId());
        assertThat(ticketCrudService.findById(created.getId())).isEmpty();
    }

    @Test
    void shouldRejectTicketWithNullClient() {
        assertThatThrownBy(() -> ticketCrudService.create(null, "EARTH", "MARS"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Client");
    }

    @Test
    void shouldRejectTicketWithNonExistingClient() {
        assertThatThrownBy(() -> ticketCrudService.create(999L, "EARTH", "MARS"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Client not found");
    }

    @Test
    void shouldRejectTicketWithNullFromPlanet() {
        assertThatThrownBy(() -> ticketCrudService.create(1L, null, "MARS"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("from planet");
    }

    @Test
    void shouldRejectTicketWithNonExistingFromPlanet() {
        assertThatThrownBy(() -> ticketCrudService.create(1L, "UNKNOWN", "MARS"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("from planet not found");
    }

    @Test
    void shouldRejectTicketWithNullToPlanet() {
        assertThatThrownBy(() -> ticketCrudService.create(1L, "EARTH", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("to planet");
    }

    @Test
    void shouldRejectTicketWithNonExistingToPlanet() {
        assertThatThrownBy(() -> ticketCrudService.create(1L, "EARTH", "UNKNOWN"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("to planet not found");
    }
}
