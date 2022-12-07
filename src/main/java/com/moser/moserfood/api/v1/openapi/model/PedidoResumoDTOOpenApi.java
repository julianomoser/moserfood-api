package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.PedidoResumoDTO;
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

    @Data
    public static class PedidosResumoEmbeddedModelOpenApi {

        private List<PedidoResumoDTO> pedidos;

    }
}
