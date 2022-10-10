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
public class EstadoDTO extends RepresentationModel<EstadoDTO> {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Paraná")
    private String nome;
}
