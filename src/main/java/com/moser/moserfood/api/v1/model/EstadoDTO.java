package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoDTO extends RepresentationModel<EstadoDTO> {

    private Long id;
    private String nome;
}
