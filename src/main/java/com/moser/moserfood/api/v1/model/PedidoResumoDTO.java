package com.moser.moserfood.api.v1.model;

import com.moser.moserfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author Juliano Moser
 */
//@JsonFilter("pedidoFilter")
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoDTO extends RepresentationModel<PedidoResumoDTO> {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private RestauranteApenasNomeDTO restaurante;
    private UsuarioDTO cliente;
}
