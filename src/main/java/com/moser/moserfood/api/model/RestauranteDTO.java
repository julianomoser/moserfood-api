package com.moser.moserfood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.moser.moserfood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class RestauranteDTO {

    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;
    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
