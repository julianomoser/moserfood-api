package com.moser.moserfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "1")
    @NotNull
    private Long produtoId;

    @ApiModelProperty(example = "5")
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @ApiModelProperty(example = "Retirar as cebolas")
    private String observacao;
}
