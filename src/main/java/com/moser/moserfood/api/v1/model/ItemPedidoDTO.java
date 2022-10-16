package com.moser.moserfood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ItemPedidoDTO extends RepresentationModel<ItemPedidoDTO> {

    @ApiModelProperty(example = "1")
    private Long produtoId;
    @ApiModelProperty(example = "NotBurguer")
    private String produtoNome;
    @ApiModelProperty(example = "2")
    private Integer quantidade;
    @ApiModelProperty(example = "22.00")
    private BigDecimal precoUnitario;
    @ApiModelProperty(example = "44.00")
    private BigDecimal precoTotal;
    @ApiModelProperty(example = "Menos picante, por favor")
    private String observacao;
}
