package com.moser.moserfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class PermissaoDTO {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "CONSULTAR_COZINHAS")
    private String nome;
    @ApiModelProperty(example = "Permite consultar cozinhas")
    private String descricao;
}
