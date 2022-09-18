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
public class SenhaInput {

    @ApiModelProperty(example = "teste123")
    @NotBlank
    private String senhaAtual;

    @ApiModelProperty(example = "teste321")
    @NotBlank
    private String novaSenha;
}
