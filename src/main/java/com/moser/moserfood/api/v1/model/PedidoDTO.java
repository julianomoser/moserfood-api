package com.moser.moserfood.api.v1.model;

import com.moser.moserfood.domain.model.enums.StatusPedido;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String codigo;
    @ApiModelProperty(example = "310.00")
    private BigDecimal subtotal;
    @ApiModelProperty(example = "12.0")
    private BigDecimal taxaFrete;
    @ApiModelProperty(example = "332.00")
    private BigDecimal valorTotal;
    @ApiModelProperty(example = "CRIADO")
    private StatusPedido status;
    @ApiModelProperty(example = "2022-09-18T01:02:18Z")
    private OffsetDateTime dataCriacao;
    @ApiModelProperty(example = "2022-09-18T01:05:18Z")
    private OffsetDateTime dataConfirmacao;
    @ApiModelProperty(example = "2022-09-18T01:22:18Z")
    private OffsetDateTime dataEntrega;
    @ApiModelProperty(example = "2022-09-18T01:03:18Z")
    private OffsetDateTime dataCancelamento;
    private RestauranteApenasNomeDTO restaurante;
    private UsuarioDTO cliente;
    private FormaPagamentoDTO formaPagamento;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itens;
}
