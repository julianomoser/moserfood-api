package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.CozinhaDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@ApiModel("RestauranteBasicoDTO")
@Getter
@Setter
public class RestauranteBasicoDTOOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Java Veg")
    private String nome;
    @ApiModelProperty(example = "12.00")
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;

}
