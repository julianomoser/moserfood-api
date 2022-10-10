package com.moser.moserfood.api.model;

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
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Curitiba")
    private String nome;
    private EstadoDTO estado;
}
