package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CozinhaInput {

    @Schema(example = "Curitiba", required = true)
    @NotBlank
    private String nome;
}
