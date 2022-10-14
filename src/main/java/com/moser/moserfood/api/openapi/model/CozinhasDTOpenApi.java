package com.moser.moserfood.api.openapi.model;

import com.moser.moserfood.api.model.CozinhaDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasDTOpenApi {
    private CozinhaEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CozinhasEmbeddedModel")
    @Data
    public static class CozinhaEmbeddedModelOpenApi {
        private List<CozinhaDTO> cozinhas;

    }
}
