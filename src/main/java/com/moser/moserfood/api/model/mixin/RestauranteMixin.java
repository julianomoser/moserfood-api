package com.moser.moserfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moser.moserfood.domain.model.Cozinha;
import com.moser.moserfood.domain.model.Endereco;
import com.moser.moserfood.domain.model.FormaPagamento;
import com.moser.moserfood.domain.model.Produto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juliano Moser
 */
public class RestauranteMixin {

    //    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private OffsetDateTime dataCadastro;
    @JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
