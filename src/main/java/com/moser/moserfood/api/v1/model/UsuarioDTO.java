package com.moser.moserfood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Plini")
    private String nome;
    @Schema(example = "plini@teste.com")
    private String email;
}
