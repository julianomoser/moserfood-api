package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juliano Moser
 */

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
