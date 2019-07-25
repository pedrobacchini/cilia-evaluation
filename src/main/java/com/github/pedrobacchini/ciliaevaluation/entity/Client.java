package com.github.pedrobacchini.ciliaevaluation.entity;

import com.github.pedrobacchini.ciliaevaluation.constraint.BrazilFullName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(max = 100)
    @BrazilFullName
    @Column(nullable = false, length = 100)
    private String name;


    @Email
    @NotNull
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Setter
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    public Client(@NotNull @Size(max = 100) @BrazilFullName String name, @Email @NotNull String email) {
        this.name = name;
        this.email = email;
    }
}
