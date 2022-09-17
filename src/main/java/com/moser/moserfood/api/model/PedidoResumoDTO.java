package com.moser.moserfood.api.model;

import com.moser.moserfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author Juliano Moser
 */
//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoDTO {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private RestauranteResumoDTO restaurante;
    private UsuarioDTO cliente;
}
