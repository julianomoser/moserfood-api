package com.moser.moserfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class EnderecoDTO {

    @ApiModelProperty(example = "00000-000")
    private String cep;
    @ApiModelProperty(example = "Rua Dos Testes")
    private String logradouro;
    @ApiModelProperty(example = "\"900\"")
    private String numero;
    @ApiModelProperty(example = "Apartamento 00")
    private String complemento;
    @ApiModelProperty(example = "Centro")
    private String bairro;
    private CidadeReumoDTO cidade;

}
