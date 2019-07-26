package com.github.pedrobacchini.ciliaevaluation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pedrobacchini.ciliaevaluation.constraint.BrazilFullName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
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

    @NotEmpty
    @Size(max = 100)
    @BrazilFullName
    @Column(nullable = false, length = 100)
    private String name;


    @Email
    @NotEmpty
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Setter
    @Temporal(TemporalType.DATE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date birthdate;

    public Client(@NotEmpty @Size(max = 100) @BrazilFullName String name, @Email @NotEmpty String email) {
        this.name = name;
        this.email = email;
    }
}
