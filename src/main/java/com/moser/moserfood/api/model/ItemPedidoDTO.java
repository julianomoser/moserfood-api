package com.moser.moserfood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ItemPedidoDTO {

    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;
}
