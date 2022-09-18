package com.moser.moserfood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.moser.moserfood.api.model.view.RestauranteView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CozinhaDTO {

    @ApiModelProperty(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @ApiModelProperty(example = "Curitiba")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
