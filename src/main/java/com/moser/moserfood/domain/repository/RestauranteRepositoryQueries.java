package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Juliano Moser
 */
public interface RestauranteRepositoryQueries {
    List<Restaurante> find(String nome,
                           BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> findByFreeShipping(String name);
}
