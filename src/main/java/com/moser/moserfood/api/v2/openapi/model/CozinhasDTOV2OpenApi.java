package com.moser.moserfood.api.v2.openapi.model;

import com.moser.moserfood.api.v1.model.CozinhaDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class CozinhasDTOV2OpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageDTOV2OpenApi page;

    @Data
    public static class CozinhasEmbeddedModelOpenApi {

        private List<CozinhaDTO> cozinhas;

    }
}
