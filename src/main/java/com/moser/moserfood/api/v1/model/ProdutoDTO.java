package com.moser.moserfood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Relation("produtos")
@Getter
@Setter
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "NotBurguer")
    private String nome;
    @ApiModelProperty(example = "Delicioso hamburger de falafel")
    private String descricao;
    @ApiModelProperty(example = "20.20")
    private BigDecimal preco;
    @ApiModelProperty(example = "true")
    private Boolean ativo;
    @ApiModelProperty(example = "true")
    private Boolean aberto;
}
