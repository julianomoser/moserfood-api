package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.model.PermissaoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@Api(tags = "Permission")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissaoDTO> listar();
}
