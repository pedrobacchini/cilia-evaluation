package com.github.pedrobacchini.ciliaevaluation.dto;

import com.github.pedrobacchini.ciliaevaluation.constraint.ClientUniqueEmail;
import com.github.pedrobacchini.ciliaevaluation.constraint.FullName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@ClientUniqueEmail
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Jackson
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

    ClientDTO(@FullName @NotEmpty @Size(max = 100) String name,
              @Email @NotEmpty @Size(max = 100) String email,
              Date birthdate) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }
}
