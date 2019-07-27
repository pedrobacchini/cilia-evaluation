package com.github.pedrobacchini.ciliaevaluation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Jackson
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 6431479951948945096L;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Range(min = 1)
    private Double price;

    @ToString.Exclude
    @Size(max = 2000)
    private String description;
}
