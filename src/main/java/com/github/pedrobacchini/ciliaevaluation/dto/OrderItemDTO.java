package com.github.pedrobacchini.ciliaevaluation.dto;

import com.github.pedrobacchini.ciliaevaluation.entity.Product;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@SuppressWarnings("WeakerAccess") //Using to parse OrderItemDTO to OrderItem
public class OrderItemDTO implements Serializable {

    private static final long serialVersionUID = 314110635188221958L;

    @Min(1)
    @NotNull
    private Integer quantity;

    @NotNull
    private Product product;
}
