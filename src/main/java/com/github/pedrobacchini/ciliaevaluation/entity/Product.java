package com.github.pedrobacchini.ciliaevaluation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Getter
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"uuid"})
public class Product implements Serializable {

    private static final long serialVersionUID = -4368619256242850842L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Setter
    @ToString.Exclude
    @Column(length = 2000)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "orderItemPK.product")
    private final Set<OrderItem> itens = new HashSet<>();

    public Product(@NotNull @Size(min = 3, max = 100) String name,
                   @NotNull @Range(min = 1) Double price) {
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public List<Order> getOrders() {
        List<Order> listOrders = new ArrayList<>();
        for(OrderItem orderItem : itens) {
            listOrders.add(orderItem.getOrder());
        }
        return listOrders;
    }

    @SuppressWarnings("unused") // using for BeanUtils.copyProperties
    public void setName(@NotNull @Size(min = 3, max = 100) String name) { this.name = name; }

    @SuppressWarnings("unused") // using for BeanUtils.copyProperties
    public void setPrice(@NotNull @Range(min = 1) Double price) { this.price = price; }
}
