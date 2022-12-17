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
public class FormaPagamentoInput {

    @Schema(example = "Cartão de crédito", required = true)
    @NotBlank
    private String descricao;
}
