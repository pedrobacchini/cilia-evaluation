package com.github.pedrobacchini.ciliaevaluation.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
@EqualsAndHashCode
class OrderItemPK implements Serializable {

    private static final long serialVersionUID = 8170526012326062835L;

    @ManyToOne
    @JoinColumn(name = "order_uuid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
}
