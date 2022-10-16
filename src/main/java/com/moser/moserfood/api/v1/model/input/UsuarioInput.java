package com.moser.moserfood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class UsuarioInput {

    @ApiModelProperty(example = "Plini", required = true)
    @NotBlank
    private String nome;
    @ApiModelProperty(example = "plini@teste.com", required = true)
    @NotBlank
    @Email
    private String email;
}
