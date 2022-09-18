package com.moser.moserfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "NotBurguer")
    @NotBlank
    private String nome;
    @ApiModelProperty(example = "Delicioso hamburger de falafel")
    @NotBlank
    private String descricao;
    @ApiModelProperty(example = "20.20")
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;
    @ApiModelProperty(example = "true")
    @NotNull
    private Boolean ativo;
}
