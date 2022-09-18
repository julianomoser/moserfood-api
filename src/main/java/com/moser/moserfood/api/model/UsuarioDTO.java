package com.moser.moserfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class UsuarioDTO {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Plini")
    private String nome;
    @ApiModelProperty(example = "plini@teste.com")
    private String email;
}
