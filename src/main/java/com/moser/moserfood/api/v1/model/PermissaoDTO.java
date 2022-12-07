package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation("permissões")
@Getter
@Setter
public class PermissaoDTO extends RepresentationModel<PermissaoDTO> {

    private Long id;
    private String nome;
    private String descricao;
}
