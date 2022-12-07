package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation("grupos")
@Getter
@Setter
public class GrupoDTO extends RepresentationModel<GrupoDTO> {

    private Long id;
    private String nome;
}
