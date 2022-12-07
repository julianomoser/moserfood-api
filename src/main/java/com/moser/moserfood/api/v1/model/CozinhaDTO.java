package com.moser.moserfood.api.v1.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.moser.moserfood.api.v1.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaDTO extends RepresentationModel<CozinhaDTO> {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
