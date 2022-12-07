package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.RestauranteBasicoDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class RestaurantesBasicoDTOOpenApi {

    private RestaurantesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class RestaurantesEmbeddedModelOpenApi {

        private List<RestauranteBasicoDTO> restaurantes;

    }
}
