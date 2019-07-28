package com.github.pedrobacchini.ciliaevaluation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_ITEM")
@EqualsAndHashCode(of = "orderItemPK")
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Hibernate
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 8146792575900360772L;

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK orderItemPK = new OrderItemPK();

    @Getter
    private Integer quantity = 0;

    @Getter
    private Double price = 0.0;

    public OrderItem(Order order, Product product, Integer quantity) {
        this.orderItemPK.setOrder(order);
        this.orderItemPK.setProduct(product);
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    @SuppressWarnings("WeakerAccess") //for Jackson
    public Double getSubTotal() { return price * quantity; }

    @JsonIgnore
    public Order getOrder() { return orderItemPK.getOrder(); }

    public void setOrder(Order order) { orderItemPK.setOrder(order); }

    public Product getProduct() { return orderItemPK.getProduct(); }

    public void setProduct(Product product) {
        orderItemPK.setProduct(product);
        this.price = product.getPrice();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{product = ").append(getProduct().getName());
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", subTotal=").append(getSubTotal());
        sb.append('}');
        return sb.toString();
    }
}
