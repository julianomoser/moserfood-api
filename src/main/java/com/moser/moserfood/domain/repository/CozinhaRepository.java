package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Juliano Moser
 */

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

    List<Cozinha> findByNomeContainingIgnoreCase(String nome);
}
