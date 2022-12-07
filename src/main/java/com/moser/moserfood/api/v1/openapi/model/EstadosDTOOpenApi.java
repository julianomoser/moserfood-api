package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.EstadoDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class EstadosDTOOpenApi {

    private EstadosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public class EstadosEmbeddedModelOpenApi {

        private List<EstadoDTO> estados;

    }
}
