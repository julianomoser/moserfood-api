package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoDTO extends RepresentationModel<RestauranteBasicoDTO> {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
}
