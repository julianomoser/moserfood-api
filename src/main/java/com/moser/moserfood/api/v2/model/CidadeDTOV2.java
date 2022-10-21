package com.moser.moserfood.api.v2.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeDTOV2 extends RepresentationModel<CidadeDTOV2> {

    @ApiModelProperty(example = "1")
    private Long idCidade;
    @ApiModelProperty(example = "Curitiba")
    private String nomeCidade;

    private String idEstado;
    private String nomeEstado;
}
