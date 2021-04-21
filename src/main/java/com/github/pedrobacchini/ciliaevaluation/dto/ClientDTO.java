package com.github.pedrobacchini.ciliaevaluation.dto;

import com.github.pedrobacchini.ciliaevaluation.constraint.ClientUniqueEmail;
import com.github.pedrobacchini.ciliaevaluation.constraint.FullName;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@ClientUniqueEmail
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 5871589647691942638L;

    @FullName
    @NotEmpty
    @Size(max = 100)
    private String name;

    @Email
    @NotEmpty
    @Size(max = 100)
    private String email;

    @ToString.Exclude
    private Date birthdate;
}
