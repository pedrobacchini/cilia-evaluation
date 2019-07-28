package com.github.pedrobacchini.ciliaevaluation.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Table(name = "\"ORDER\"")
@EqualsAndHashCode(of = {"uuid"})
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Hibernate
public class Order implements Serializable {

    private static final long serialVersionUID = 8351242219803484217L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "client_uuid")
    private Client client;

    @OneToMany(mappedBy = "orderItemPK.order")
    private Set<OrderItem> itens = new HashSet<>();

    public Order(Client client) { this.client = client; }

    @SuppressWarnings("WeakerAccess") //for Jackson
    public double getTotal() { return itens.stream().mapToDouble(OrderItem::getSubTotal).sum(); }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("uuid=").append(uuid);
        sb.append(", client=").append(client.getName());
        sb.append(", itens=");
        for(OrderItem orderItem : getItens())
            sb.append(orderItem.toString()).append(", ");
        sb.append("total=").append(getTotal());
        sb.append('}');
        return sb.toString();
    }
}
