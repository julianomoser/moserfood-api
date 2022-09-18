package com.moser.moserfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class EnderecoInput {

    @ApiModelProperty(example = "00000-000")
    @NotBlank
    private String cep;
    @ApiModelProperty(example = "Rua Dos Testes")
    @NotBlank
    private String logradouro;
    @ApiModelProperty(example = "900")
    @NotBlank
    private String numero;
    @ApiModelProperty(example = "Apartamento 00")
    private String complemento;
    @ApiModelProperty(example = "Centro")
    @NotBlank
    private String bairro;
    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
