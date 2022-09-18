package com.moser.moserfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ProdutoDTO {

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
