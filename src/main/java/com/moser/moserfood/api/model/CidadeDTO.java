package com.moser.moserfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CidadeDTO {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Curitiba")
    private String nome;
    private EstadoDTO estado;
}
