package com.moser.moserfood.api.openapi.model;

import com.moser.moserfood.api.model.CidadeDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {
    private CidadeEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public static class CidadeEmbeddedModelOpenApi {
        private List<CidadeDTO> cidades;

    }
}
