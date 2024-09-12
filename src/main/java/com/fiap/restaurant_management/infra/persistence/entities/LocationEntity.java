package com.fiap.restaurant_management.infra.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {

    private String cep;

    private Integer numero;

    private String complemento;

}
