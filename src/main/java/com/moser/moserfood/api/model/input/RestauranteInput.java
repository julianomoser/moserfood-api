package com.moser.moserfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Setter
@Getter
public class RestauranteInput {

    @ApiModelProperty(example = "Java Veg")
    @NotBlank
    private String nome;
    @ApiModelProperty(example = "12.00")
    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;
    @Valid
    @NotNull
    private CozinhaIdInput cozinha;
    @Valid
    @NotNull
    private EnderecoInput endereco;
}
