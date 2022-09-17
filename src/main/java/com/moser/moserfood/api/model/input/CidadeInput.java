package com.moser.moserfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CidadeInput {


    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
