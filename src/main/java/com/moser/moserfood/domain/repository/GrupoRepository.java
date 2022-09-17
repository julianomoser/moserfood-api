package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juliano Moser
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
