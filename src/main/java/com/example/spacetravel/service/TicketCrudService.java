package com.example.spacetravel.service;

import com.example.spacetravel.entity.Client;
import com.example.spacetravel.entity.Planet;
import com.example.spacetravel.entity.Ticket;
import com.example.spacetravel.repository.ClientRepository;
import com.example.spacetravel.repository.PlanetRepository;
import com.example.spacetravel.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TicketCrudService {

    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;
    private final PlanetRepository planetRepository;

    public TicketCrudService(
            TicketRepository ticketRepository,
            ClientRepository clientRepository,
            PlanetRepository planetRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.clientRepository = clientRepository;
        this.planetRepository = planetRepository;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return ticketRepository.findById(id);
    }

    @Transactional
    public Ticket create(Long clientId, String fromPlanetId, String toPlanetId) {
        Client client = findExistingClient(clientId);
        Planet fromPlanet = findExistingPlanet(fromPlanetId, "from planet");
        Planet toPlanet = findExistingPlanet(toPlanetId, "to planet");
        return ticketRepository.save(new Ticket(client, fromPlanet, toPlanet));
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket must not be null");
        }

        Client client = ticket.getClient();
        Planet fromPlanet = ticket.getFromPlanet();
        Planet toPlanet = ticket.getToPlanet();

        ticket.setClient(findExistingClient(client == null ? null : client.getId()));
        ticket.setFromPlanet(findExistingPlanet(fromPlanet == null ? null : fromPlanet.getId(), "from planet"));
        ticket.setToPlanet(findExistingPlanet(toPlanet == null ? null : toPlanet.getId(), "to planet"));

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket update(Long id, Long clientId, String fromPlanetId, String toPlanetId) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + id));

        ticket.setClient(findExistingClient(clientId));
        ticket.setFromPlanet(findExistingPlanet(fromPlanetId, "from planet"));
        ticket.setToPlanet(findExistingPlanet(toPlanetId, "to planet"));

        return ticketRepository.save(ticket);
    }

    @Transactional
    public void delete(Long id) {
        if (id == null || !ticketRepository.existsById(id)) {
            throw new IllegalArgumentException("Ticket not found: " + id);
        }
        ticketRepository.deleteById(id);
    }

    private Client findExistingClient(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client must not be null");
        }
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + clientId));
    }

    private Planet findExistingPlanet(String planetId, String fieldName) {
        if (planetId == null || planetId.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
        String normalizedId = planetId.trim().toUpperCase();
        return planetRepository.findById(normalizedId)
                .orElseThrow(() -> new IllegalArgumentException(fieldName + " not found: " + normalizedId));
    }
}
