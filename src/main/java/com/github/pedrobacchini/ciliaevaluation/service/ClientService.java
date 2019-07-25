package com.github.pedrobacchini.ciliaevaluation.service;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<Client> getAllClients();

    Client getClientById(UUID uuid);

    boolean exists(String email);

    Client createClient(Client client);
}
