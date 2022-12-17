package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class SenhaInput {

    @Schema(example = "teste123", required = true)
    @NotBlank
    private String senhaAtual;

    @Schema(example = "123teste", required = true)
    @NotBlank
    private String novaSenha;
}
