package com.github.pedrobacchini.ciliaevaluation.service.impl;

import com.github.pedrobacchini.ciliaevaluation.config.CustomMessageSource;
import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import com.github.pedrobacchini.ciliaevaluation.service.ClientService;
import com.github.pedrobacchini.ciliaevaluation.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CustomMessageSource customMessageSource;

    public ClientServiceImpl(ClientRepository clientRepository,
                             CustomMessageSource customMessageSource) {
        this.clientRepository = clientRepository;
        this.customMessageSource = customMessageSource;
    }

    @Override
    public List<Client> getAllClients() { return clientRepository.findAll(); }

    @Override
    public Client getClientById(UUID uuid) {
        return clientRepository.findById(uuid)
                .orElseThrow(() ->
                        new ObjectNotFoundException(customMessageSource
                                .getMessage("object-not-found", uuid, Client.class.getName())));
    }
}
