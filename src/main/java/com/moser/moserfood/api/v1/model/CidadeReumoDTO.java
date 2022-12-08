package com.moser.moserfood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CidadeReumoDTO extends RepresentationModel<CidadeReumoDTO> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Curitiba")
    private String nome;

    @Schema(example = "Paraná")
    private String estado;
}
