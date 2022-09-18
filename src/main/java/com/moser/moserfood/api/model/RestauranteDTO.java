package com.moser.moserfood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.moser.moserfood.api.model.view.RestauranteView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class RestauranteDTO {

    @ApiModelProperty(example = "1")
    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;
    @ApiModelProperty(example = "Java Veg")
    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;
    @ApiModelProperty(example = "12.00")
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
