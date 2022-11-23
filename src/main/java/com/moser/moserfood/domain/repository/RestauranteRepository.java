package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author Juliano Moser
 */
@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();

    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
//    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultByNome(String nome, @Param("id") Long cozinhaId);
//    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
    Optional<Restaurante> getFirstByNomeContaining(String nome);
    List<Restaurante> findTop2ByNomeContaining(String nome);
    boolean existsByNome(String nome);
    int countByCozinhaId(Long cozinhaId);
    boolean existsResponsavel(Long restauranteId, Long usuarioId);

}
