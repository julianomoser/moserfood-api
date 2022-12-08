package com.moser.moserfood.api.v1.model;

import com.moser.moserfood.domain.model.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String codigo;
    @Schema(example = "310.00")
    private BigDecimal subtotal;
    @Schema(example = "12.0")
    private BigDecimal taxaFrete;
    @Schema(example = "332.00")
    private BigDecimal valorTotal;
    @Schema(example = "CRIADO")
    private StatusPedido status;
    @Schema(example = "2022-09-18T01:02:18Z")
    private OffsetDateTime dataCriacao;
    @Schema(example = "2022-09-18T01:05:18Z")
    private OffsetDateTime dataConfirmacao;
    @Schema(example = "2022-09-18T01:22:18Z")
    private OffsetDateTime dataEntrega;
    @Schema(example = "2022-09-18T01:03:18Z")
    private OffsetDateTime dataCancelamento;
    private RestauranteApenasNomeDTO restaurante;
    private UsuarioDTO cliente;
    private FormaPagamentoDTO formaPagamento;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itens;
}
