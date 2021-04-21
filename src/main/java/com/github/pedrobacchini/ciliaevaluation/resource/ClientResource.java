package com.github.pedrobacchini.ciliaevaluation.resource;

import com.github.pedrobacchini.ciliaevaluation.dto.ClientDTO;
import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.event.ResourceCreatedEvent;
import com.github.pedrobacchini.ciliaevaluation.service.ClientService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/client")
public class ClientResource {

    private final ClientService clientService;
    private final ApplicationEventPublisher publisher;

    public ClientResource(ClientService clientService,
                          ApplicationEventPublisher publisher) {
        this.clientService = clientService;
        this.publisher = publisher;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAllClients() { return clientService.getAllClients(); }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable("uuid") String uuid) {
        Client client = clientService.getClientById(UUID.fromString(uuid));
        return ResponseEntity.ok(client);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientDTO clientDTO, HttpServletResponse response) {
        Client createdClient = clientService.createClient(fromDTO(clientDTO));
        publisher.publishEvent(new ResourceCreatedEvent(this, response, createdClient.getUuid()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@PathVariable("uuid") String uuid, @RequestBody @Valid ClientDTO clientDTO) {
        Client client = clientService.updateClient(UUID.fromString(uuid), fromDTO(clientDTO));
        return ResponseEntity.ok(client);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> deleteClient(@PathVariable("uuid") String uuid) {
        clientService.deleteClient(UUID.fromString(uuid));
        return ResponseEntity.noContent().build();
    }

    private Client fromDTO(ClientDTO clientDTO) {
        Client client = new Client(clientDTO.getName(), clientDTO.getEmail());
        client.setBirthdate(clientDTO.getBirthdate());
        return client;
    }
}
