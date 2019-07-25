package com.github.pedrobacchini.ciliaevaluation.service;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;

import java.util.UUID;

public interface ClientService {

    Client getClientById(UUID uuid);
}
