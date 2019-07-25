package com.github.pedrobacchini.ciliaevaluation.resource;

import com.github.pedrobacchini.ciliaevaluation.config.CustomMessageSource;
import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import com.github.pedrobacchini.ciliaevaluation.event.ResourceCreatedEvent;
import com.github.pedrobacchini.ciliaevaluation.resource.exception.ObjectAlreadyExistException;
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
    private final CustomMessageSource customMessageSource;

    public ClientResource(ClientService clientService,
                          ApplicationEventPublisher publisher,
                          CustomMessageSource customMessageSource) {
        this.clientService = clientService;
        this.publisher = publisher;
        this.customMessageSource = customMessageSource;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Client> getAllClients() { return clientService.getAllClients(); }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> getClientById(@PathVariable("uuid") String uuid) {
        Client client = clientService.getClientById(UUID.fromString(uuid));
        return ResponseEntity.ok(client);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody @Valid Client client, HttpServletResponse response) {
        if(clientService.exists(client.getEmail()))
            throw new ObjectAlreadyExistException(customMessageSource.getMessage("object-already-exist", client.getEmail(), Client.class.getName()));
        Client savedClient = clientService.createClient(client);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedClient.getUuid()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }
}
