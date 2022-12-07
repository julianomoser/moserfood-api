package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.CozinhaDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class RestauranteBasicoDTOOpenApi {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;

}
