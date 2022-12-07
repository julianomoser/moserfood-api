package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    private Long id;
    private String nome;
    private String email;
}
