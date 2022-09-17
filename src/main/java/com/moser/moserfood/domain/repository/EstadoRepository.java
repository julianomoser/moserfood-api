package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Juliano Moser
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
