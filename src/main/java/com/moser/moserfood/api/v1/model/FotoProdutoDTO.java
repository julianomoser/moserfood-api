package com.moser.moserfood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation("fotos")
@Getter
@Setter
public class FotoProdutoDTO extends RepresentationModel<FotoProdutoDTO> {

    private String nomeArquivo;

    private String descricao;

    private String contentType;

    private Long tamanho;
}
