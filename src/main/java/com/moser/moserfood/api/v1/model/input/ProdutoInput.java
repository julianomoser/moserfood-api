package com.moser.moserfood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ProdutoInput {

    @Schema(example = "NotBurguer", required = true)
    @NotBlank
    private String nome;
    @Schema(example = "Delicioso hamburger de falafel", required = true)
    @NotBlank
    private String descricao;
    @Schema(example = "20.20", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;
    @Schema(example = "true", required = true)
    @NotNull
    private Boolean ativo;
}
