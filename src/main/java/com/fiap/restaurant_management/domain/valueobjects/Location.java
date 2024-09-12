package com.fiap.restaurant_management.domain.valueobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {

    private String cep;

    private Integer number;

    private String complement;

    public Location(String cep, Integer numero, String complemento) {
        this.cep = cep;
        this.number = numero;
        this.complement = complemento;
    }
}
