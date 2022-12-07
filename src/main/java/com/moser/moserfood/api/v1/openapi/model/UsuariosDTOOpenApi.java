package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.UsuarioDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class UsuariosDTOOpenApi {

    private UsuariosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class UsuariosEmbeddedModelOpenApi {

        private List<UsuarioDTO> usuarios;

    }
}
