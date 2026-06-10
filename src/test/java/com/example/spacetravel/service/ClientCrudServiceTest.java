package com.example.spacetravel.service;

import com.example.spacetravel.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class ClientCrudServiceTest {

    @Autowired
    private ClientCrudService clientCrudService;

    @Test
    void shouldCreateReadUpdateAndDeleteClient() {
        Client created = clientCrudService.create("Test Client");

        assertThat(created.getId()).isNotNull();
        assertThat(clientCrudService.findById(created.getId())).isPresent();

        Client updated = clientCrudService.update(created.getId(), "Updated Client");
        assertThat(updated.getName()).isEqualTo("Updated Client");

        clientCrudService.delete(created.getId());
        assertThat(clientCrudService.findById(created.getId())).isEmpty();
    }

    @Test
    void shouldRejectInvalidClientName() {
        assertThatThrownBy(() -> clientCrudService.create("Al"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
