package com.moser.moserfood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.moser.moserfood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CozinhaDTO {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
