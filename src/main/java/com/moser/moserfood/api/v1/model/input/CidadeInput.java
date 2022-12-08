package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CidadeInput {

    @Schema(example = "Curitiba", required = true)
    @NotBlank
    private String nome;
    @Valid
    @NotNull
    private EstadoIdInput estado;
}
