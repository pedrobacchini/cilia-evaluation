package com.github.pedrobacchini.ciliaevaluation.service.impl;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.dto.ClientRegister;
import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import com.github.pedrobacchini.ciliaevaluation.resource.exception.ObjectAlreadyExistException;
import com.github.pedrobacchini.ciliaevaluation.service.ClientService;
import com.github.pedrobacchini.ciliaevaluation.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final LocaleMessageSource localeMessageSource;

    public ClientServiceImpl(ClientRepository clientRepository,
                             LocaleMessageSource localeMessageSource) {
        this.clientRepository = clientRepository;
        this.localeMessageSource = localeMessageSource;
    }

    @Override
    public List<Client> getAllClients() { return clientRepository.findAll(); }

    @Override
    public Client getClientById(UUID uuid) {
        return clientRepository.findById(uuid)
                .orElseThrow(() ->
                        new ObjectNotFoundException(localeMessageSource
                                .getMessage("object-not-found", uuid, Client.class.getName())));
    }

    @Override
    @Transactional
    public Client createClient(ClientRegister clientRegister) {
        clientRepository.findByEmail(clientRegister.getEmail())
                .ifPresent(found -> {
                    throw new ObjectAlreadyExistException(localeMessageSource
                            .getMessage("object-already-exist", found.getEmail(), Client.class.getName()));
                });
        Client client = fromDTO(clientRegister);
        return clientRepository.save(client);
    }

    private Client fromDTO(ClientRegister clientRegister) {
        Client client = new Client(clientRegister.getName(), clientRegister.getEmail());
        client.setBirthdate(clientRegister.getBirthdate());
        return client;
    }
}
