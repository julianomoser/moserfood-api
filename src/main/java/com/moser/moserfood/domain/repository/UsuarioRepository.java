package com.moser.moserfood.domain.repository;

import com.moser.moserfood.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Juliano Moser
 */
@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
