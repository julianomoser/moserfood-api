package com.moser.moserfood.api.v2.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CidadeInputV2 {

    @NotBlank
    private String nomeCidade;

    @NotNull
    private Long idEstado;
}
