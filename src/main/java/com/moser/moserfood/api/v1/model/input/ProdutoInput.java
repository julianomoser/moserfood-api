package com.moser.moserfood.api.v1.model.input;

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

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;
    @NotNull
    private Boolean ativo;
}
