package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.PermissaoDTO;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
public interface PermissaoControllerOpenApi {

    CollectionModel<PermissaoDTO> listar();
}
