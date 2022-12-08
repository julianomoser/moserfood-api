package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.PermissaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissões")
public interface PermissaoControllerOpenApi {

    @Operation(summary = "Lista as permissões")
    CollectionModel<PermissaoDTO> listar();
}
