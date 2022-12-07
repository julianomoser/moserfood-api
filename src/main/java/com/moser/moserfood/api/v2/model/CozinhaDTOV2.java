package com.moser.moserfood.api.v2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaDTOV2 extends RepresentationModel<CozinhaDTOV2> {

    private Long idCozinha;

    private String nomeCozinha;
}
