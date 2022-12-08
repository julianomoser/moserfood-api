package com.moser.moserfood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "1")
    private Long id;
    @Schema(example = "NotBurguer")
    private String nome;
    @Schema(example = "Delicioso hamburger de falafel")
    private String descricao;
    @Schema(example = "20.20")
    private BigDecimal preco;
    @Schema(example = "true")
    private Boolean ativo;
    @Schema(example = "true")
    private Boolean aberto;
}
