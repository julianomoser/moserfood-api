package com.moser.moserfood.api.openapi.model;

import com.moser.moserfood.api.model.PedidoResumoDTO;
import io.swagger.annotations.ApiModel;
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
public class PedidoResumoDTOOpenApi {

    private PedidosResumoEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("PedidosResumoEmbeddedModel")
    @Data
    public static class PedidosResumoEmbeddedModelOpenApi {

        private List<PedidoResumoDTO> pedidos;

    }
}
