package com.moser.moserfood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "1")
    private Long produtoId;
    @Schema(example = "NotBurguer")
    private String produtoNome;
    @Schema(example = "2")
    private Integer quantidade;
    @Schema(example = "22.00")
    private BigDecimal precoUnitario;
    @Schema(example = "44.00")
    private BigDecimal precoTotal;
    @Schema(example = "Menos picante, por favor")
    private String observacao;
}
