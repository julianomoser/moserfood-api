package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @Operation(summary = "Lista os usuários responsáveis associados a restaurante")
    CollectionModel<UsuarioDTO> listar(Long restauranteId);

    @Operation(summary = "Associação de restaurante com usuário responsável")
    ResponseEntity<Void> desassociar(Long restauranteId, Long usuarioId);

    @Operation(summary = "Desassociação de restaurante com usuário responsável")
    ResponseEntity<Void> associar(Long restauranteId, Long usuarioId);
}
