package com.moser.moserfood.api.v1.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.moser.moserfood.api.v1.model.view.RestauranteView;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @ApiModelProperty(example = "Curitiba")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
