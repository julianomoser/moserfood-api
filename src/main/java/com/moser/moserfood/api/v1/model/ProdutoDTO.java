package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Relation("produtos")
@Getter
@Setter
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;
    private Boolean aberto;
}
