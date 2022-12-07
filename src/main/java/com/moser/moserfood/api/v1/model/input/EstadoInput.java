package com.moser.moserfood.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class EstadoInput {

    @NotBlank
    private String nome;
}
