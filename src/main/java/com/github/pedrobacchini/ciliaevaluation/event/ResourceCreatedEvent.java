package com.github.pedrobacchini.ciliaevaluation.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Getter
public class ResourceCreatedEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private UUID uuid;

    public ResourceCreatedEvent(Object source, HttpServletResponse response, UUID uuid) {
        super(source);
        this.response = response;
        this.uuid = uuid;
    }
}
