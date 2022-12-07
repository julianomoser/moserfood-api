package com.moser.moserfood.api.v1.openapi.model;

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
public class CozinhasDTOpenApi {
    private CozinhaEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @Data
    public static class CozinhaEmbeddedModelOpenApi {
        private List<CozinhaDTO> cozinhas;

    }
}
