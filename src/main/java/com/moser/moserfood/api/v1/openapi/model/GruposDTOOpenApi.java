package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.GrupoDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class GruposDTOOpenApi {

    private GruposEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public class GruposEmbeddedModelOpenApi {

        private List<GrupoDTO> grupos;

    }
}
