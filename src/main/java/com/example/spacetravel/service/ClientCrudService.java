package com.example.spacetravel.service;

import com.example.spacetravel.entity.Client;
import com.example.spacetravel.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientCrudService {

    private final ClientRepository clientRepository;

    public ClientCrudService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return clientRepository.findById(id);
    }

    @Transactional
    public Client create(String name) {
        validateName(name);
        return clientRepository.save(new Client(name.trim()));
    }

    @Transactional
    public Client update(Long id, String name) {
        validateName(name);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + id));
        client.setName(name.trim());
        return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long id) {
        if (id == null || !clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client not found: " + id);
        }
        clientRepository.deleteById(id);
    }

    private void validateName(String name) {
        if (name == null || name.trim().length() < 3 || name.trim().length() > 200) {
            throw new IllegalArgumentException("Client name must contain from 3 to 200 characters");
        }
    }
}
