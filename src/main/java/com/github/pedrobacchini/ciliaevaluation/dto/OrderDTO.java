package com.github.pedrobacchini.ciliaevaluation.dto;

import com.github.pedrobacchini.ciliaevaluation.entity.Client;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 410659270166746110L;

    @NotNull
    private Client client;

    @Valid
    @NotEmpty(message = "{com.github.pedrobacchini.ciliaevaluation.dto.OrderDTO.NotEmpty.message}")
    private final Set<OrderItemDTO> itens = new HashSet<>();
}
