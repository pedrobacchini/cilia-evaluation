package com.github.pedrobacchini.ciliaevaluation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pedrobacchini.ciliaevaluation.constraint.FullName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@ToString
@EqualsAndHashCode(of = {"uuid"})
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Hibernate
public class Client implements Serializable {

    private static final long serialVersionUID = -6701152753907592400L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @Column(nullable = false, length = 100)
    private String name;


    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Setter
    @ToString.Exclude
    @Temporal(TemporalType.DATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date birthdate;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @SuppressWarnings("unused") // using for BeanUtils.copyProperties
    public void setName(@FullName @NotEmpty @Size(max = 100) String name) { this.name = name; }

    @SuppressWarnings("unused") // using for BeanUtils.copyProperties
    public void setEmail(@Email @NotEmpty @Size(max = 100) String email) { this.email = email; }
}
