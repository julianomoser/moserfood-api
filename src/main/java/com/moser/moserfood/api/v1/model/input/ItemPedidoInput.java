package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ItemPedidoInput {

    @Schema(example = "1", required = true)
    @NotNull
    private Long produtoId;
    @Schema(example = "5", required = true)
    @NotNull
    @PositiveOrZero
    private Integer quantidade;
    @Schema(example = "Menos picante, por favor", required = true)
    private String observacao;
}
