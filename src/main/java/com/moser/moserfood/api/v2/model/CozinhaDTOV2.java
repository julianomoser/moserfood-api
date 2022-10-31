package com.moser.moserfood.api.v2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@ApiModel("CozinhaModel")
@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaDTOV2 extends RepresentationModel<CozinhaDTOV2> {

    @ApiModelProperty(example = "1")
    private Long idCozinha;

    @ApiModelProperty(example = "Brasileira")
    private String nomeCozinha;
}
