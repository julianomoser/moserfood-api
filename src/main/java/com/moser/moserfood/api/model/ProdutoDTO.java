package com.moser.moserfood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;
    private Boolean aberto;
}
