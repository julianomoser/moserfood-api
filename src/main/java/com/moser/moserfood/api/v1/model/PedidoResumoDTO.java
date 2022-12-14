package com.moser.moserfood.api.v1.model;

import com.moser.moserfood.domain.model.enums.StatusPedido;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "2022-09-29T16:52:04Z")
    private OffsetDateTime dataCriacao;
    private RestauranteApenasNomeDTO restaurante;
    private UsuarioDTO cliente;
}
