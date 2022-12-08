package com.moser.moserfood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class EnderecoDTO {

    @Schema(example = "00000-000")
    private String cep;
    @Schema(example = "Rua Dos Testes")
    private String logradouro;
    @Schema(example = "\"900\"")
    private String numero;
    @Schema(example = "Apartamento 00")
    private String complemento;
    @Schema(example = "Centro")
    private CidadeReumoDTO cidade;

}
