package com.moser.moserfood.api.v1.model;

import com.moser.moserfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoDTO extends RepresentationModel<PedidoDTO> {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;
    private RestauranteApenasNomeDTO restaurante;
    private UsuarioDTO cliente;
    private FormaPagamentoDTO formaPagamento;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itens;
}
