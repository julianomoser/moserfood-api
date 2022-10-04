package com.moser.moserfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class EstadoInput {

    @ApiModelProperty(example = "1", required = true)
    @NotBlank
    private String nome;
}
