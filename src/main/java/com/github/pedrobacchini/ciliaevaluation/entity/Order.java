package com.github.pedrobacchini.ciliaevaluation.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@ToString
@Table(name = "\"ORDER\"")
@EqualsAndHashCode(of = {"uuid"})
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Hibernate
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "client_uuid")
    private Client client;

    public Order(Client client) {
        this.client = client;
    }
}
