package com.moser.moserfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Curitiba")
    private String nome;
    private EstadoDTO estado;
}
