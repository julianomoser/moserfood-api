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
public class CozinhaInput {

    @ApiModelProperty(example = "Vegetariana")
    @NotBlank
    private String nome;
}
