package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.EstadoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@ApiModel("EstadosModel")
@Data
public class EstadosDTOOpenApi {

    private EstadosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EstadosEmbeddedModel")
    @Data
    public class EstadosEmbeddedModelOpenApi {

        private List<EstadoDTO> estados;

    }
}
