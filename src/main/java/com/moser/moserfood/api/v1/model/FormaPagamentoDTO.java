package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoDTO extends RepresentationModel<FormaPagamentoDTO> {

    private Long id;
    private String descricao;
}
