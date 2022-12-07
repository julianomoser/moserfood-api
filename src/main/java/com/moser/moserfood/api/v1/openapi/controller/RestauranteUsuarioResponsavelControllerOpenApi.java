package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.UsuarioDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    CollectionModel<UsuarioDTO> listar(Long restauranteId);

    ResponseEntity<Void> desassociar(Long restauranteId, Long usuarioId);

    ResponseEntity<Void> associar(Long restauranteId, Long usuarioId);
}
