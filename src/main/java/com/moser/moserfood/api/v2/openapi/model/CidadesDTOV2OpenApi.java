package com.moser.moserfood.api.v2.openapi.model;

import com.moser.moserfood.api.v2.model.CidadeDTOV2;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
public class CidadesDTOV2OpenApi {


    private CidadesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class CidadesEmbeddedModelOpenApi {

        private List<CidadeDTOV2> cidades;

    }
}
