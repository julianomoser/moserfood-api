package com.moser.moserfood.api.v1.model.input;

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

    @ApiModelProperty(example = "00000-000", required = true)
    @NotBlank
    private String cep;
    @ApiModelProperty(example = "Rua Dos Testes", required = true)
    @NotBlank
    private String logradouro;
    @ApiModelProperty(example = "\"900\"", required = true)
    @NotBlank
    private String numero;
    @ApiModelProperty(example = "Apartamento 00")
    private String complemento;
    @ApiModelProperty(example = "Centro", required = true)
    @NotBlank
    private String bairro;
    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
