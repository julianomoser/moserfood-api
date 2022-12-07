package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.CidadeDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class CidadesModelOpenApi {
    private CidadeEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class CidadeEmbeddedModelOpenApi {
        private List<CidadeDTO> cidades;

    }
}
