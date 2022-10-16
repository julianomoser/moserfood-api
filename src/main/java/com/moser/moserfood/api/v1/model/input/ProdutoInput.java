package com.moser.moserfood.api.v1.model.input;

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

    @ApiModelProperty(example = "NotBurguer", required = true)
    @NotBlank
    private String nome;
    @ApiModelProperty(example = "Delicioso hamburger de falafel", required = true)
    @NotBlank
    private String descricao;
    @ApiModelProperty(example = "20.20", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;
    @ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean ativo;
}
