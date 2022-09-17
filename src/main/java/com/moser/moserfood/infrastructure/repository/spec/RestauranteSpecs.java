package com.moser.moserfood.infrastructure.repository.spec;

import com.moser.moserfood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * @author Juliano Moser
 */
public class RestauranteSpecs {

    public static Specification<Restaurante> byFreeShipping() {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO));
    }

    public static Specification<Restaurante> bySimilarName(String name) {
        return ((root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("nome"), "%" + name + "%"));
    }

}
