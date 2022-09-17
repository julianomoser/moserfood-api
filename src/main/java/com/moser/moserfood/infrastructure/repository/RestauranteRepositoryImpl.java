package com.moser.moserfood.infrastructure.repository;

import com.moser.moserfood.domain.model.Restaurante;
import com.moser.moserfood.domain.repository.RestauranteRepository;
import com.moser.moserfood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.moser.moserfood.infrastructure.repository.spec.RestauranteSpecs.byFreeShipping;
import static com.moser.moserfood.infrastructure.repository.spec.RestauranteSpecs.bySimilarName;

/**
 * @author Juliano Moser
 */
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome,
                                  BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//        var jpql = new StringBuilder();
//        jpql.append("from Restaurante where 0 = 0 ");
//
//        var parameter = new HashMap<String, Object>();
//
//        if (StringUtils.hasLength(nome)) {
//            jpql.append("and nome like :nome ");
//            parameter.put("nome", "%" + nome + "%");
//        }
//
//        if (taxaFreteInicial != null) {
//            jpql.append("and taxaFrete >= :taxaInicial ");
//            parameter.put("taxaInicial", taxaFreteInicial);
//        }
//
//        if (taxaFreteInicial != null) {
//            jpql.append("and taxaFrete <= :taxaFinal ");
//            parameter.put("taxaFinal", taxaFreteFinal);
//        }
//
//        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);

//        parameter.forEach(query::setParameter);

        var builder = entityManager.getCriteriaBuilder();
        var criteria = builder.createQuery(Restaurante.class);
        var root = criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Restaurante> findByFreeShipping(String name) {
        return restauranteRepository.findAll(byFreeShipping().and(bySimilarName(name)));
    }
}
