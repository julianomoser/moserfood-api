package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.PermissaoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
public interface PermissaoControllerOpenApi {

    CollectionModel<PermissaoDTO> listar();
}
