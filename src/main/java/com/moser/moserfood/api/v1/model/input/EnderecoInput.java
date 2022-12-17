package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class EnderecoInput {

    @Schema(example = "00000-000", required = true)
    @NotBlank
    private String cep;
    @Schema(example = "Rua Dos Testes", required = true)
    @NotBlank
    private String logradouro;
    @Schema(example = "\"900\"", required = true)
    @NotBlank
    private String numero;
    @Schema(example = "Apartamento 00")
    private String complemento;
    @Schema(example = "Centro", required = true)
    @NotBlank
    private String bairro;
    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
