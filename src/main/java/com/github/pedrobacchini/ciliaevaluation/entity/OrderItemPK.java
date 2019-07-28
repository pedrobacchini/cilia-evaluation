package com.github.pedrobacchini.ciliaevaluation.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
        //For Hibernate
class OrderItemPK implements Serializable {

    private static final long serialVersionUID = 8170526012326062835L;

    @ManyToOne
    @JoinColumn(name = "order_uuid")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;

    OrderItemPK(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
