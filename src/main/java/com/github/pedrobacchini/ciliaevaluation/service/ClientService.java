package com.github.pedrobacchini.ciliaevaluation.service;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<Client> getAllClients();

    Client getClientById(UUID uuid);

    Client createClient(Client client);

    Client updateClient(UUID uuid, Client client);

    void deleteClient(UUID uuid);
}
