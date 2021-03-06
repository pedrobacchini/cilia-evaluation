package com.github.pedrobacchini.ciliaevaluation.service.impl;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.exception.ObjectIntegrityViolationException;
import com.github.pedrobacchini.ciliaevaluation.exception.ObjectNotFoundException;
import com.github.pedrobacchini.ciliaevaluation.repository.ClientRepository;
import com.github.pedrobacchini.ciliaevaluation.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
final class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final LocaleMessageSource localeMessageSource;

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
    public Client createClient(Client client) { return clientRepository.save(client); }

    @Override
    public Client updateClient(UUID uuid, Client client) {
        Client savedClient = getClientById(uuid);
        BeanUtils.copyProperties(client, savedClient);
        return clientRepository.save(savedClient);
    }

    @Override
    public void deleteClient(UUID uuid) {
        try {
            clientRepository.deleteById(uuid);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFoundException(localeMessageSource
                    .getMessage("object-not-found", uuid, Client.class.getName()));
        } catch (DataIntegrityViolationException e) {
            throw new ObjectIntegrityViolationException(e.getMessage());
        }
    }
}
