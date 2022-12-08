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
@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoDTO extends RepresentationModel<RestauranteBasicoDTO> {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Java Veg")
    private String nome;
    @Schema(example = "12.00")
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
}
