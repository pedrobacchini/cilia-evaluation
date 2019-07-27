package com.github.pedrobacchini.ciliaevaluation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@ToString
@EqualsAndHashCode(of = {"uuid"})
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Hibernate
public class Product implements Serializable {

    private static final long serialVersionUID = -4368619256242850842L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @NotEmpty
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Range(min = 1)
    @Column(nullable = false)
    private Double price;

    @Setter
    @Size(max = 2000)
    @Column(length = 2000)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    public Product(@NotEmpty @Size(max = 100) String name, @NotEmpty @Range(min = 1) Double price) {
        this.name = name;
        this.price = price;
    }
}
