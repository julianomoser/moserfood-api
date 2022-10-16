package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.UsuarioDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@ApiModel("UsuariosModel")
@Data
public class UsuariosDTOOpenApi {

    private UsuariosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("UsuariosEmbeddedModel")
    @Data
    public static class UsuariosEmbeddedModelOpenApi {

        private List<UsuarioDTO> usuarios;

    }
}
