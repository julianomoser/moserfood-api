package com.moser.moserfood.api.openapi.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.moser.moserfood.api.model.CozinhaDTO;
import com.moser.moserfood.api.model.view.RestauranteView;
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
