package com.github.pedrobacchini.ciliaevaluation.resource;

import com.github.pedrobacchini.ciliaevaluation.dto.ClientRegister;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Client> getAllClients() { return clientService.getAllClients(); }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable("uuid") String uuid) {
        Client client = clientService.getClientById(UUID.fromString(uuid));
        return ResponseEntity.ok(client);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientRegister clientRegister, HttpServletResponse response) {
        Client createdClient = clientService.createClient(clientRegister);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, createdClient.getUuid()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

//    @PutMapping("/{uuid}")
//    public ResponseEntity<Client> updateClient(@PathVariable("uuid") String uuid, @RequestBody Client client) {
//        return
//        return userService.update(id, user);
//    }
}
