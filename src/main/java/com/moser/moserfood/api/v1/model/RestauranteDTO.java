package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Relation("restaurantes")
@Getter
@Setter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

    //    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private Long id;
    //    @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
    private String nome;
    //    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
    //    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
