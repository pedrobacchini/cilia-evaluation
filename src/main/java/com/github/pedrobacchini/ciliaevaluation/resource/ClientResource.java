package com.github.pedrobacchini.ciliaevaluation.resource;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.service.ClientService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) { this.clientService = clientService; }

    @GetMapping
    public List<Client> getAllClients() { return clientService.getAllClients(); }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable("uuid") String uuid) {
        Client client = clientService.getClientById(UUID.fromString(uuid));
        return ResponseEntity.ok(client);
    }
}
