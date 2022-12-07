package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.FormaPagamentoDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class FormasPagamentoDTOOpenApi {

    private FormasPagamentoEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public static class FormasPagamentoEmbeddedModelOpenApi {

        private List<FormaPagamentoDTO> formasPagamento;

    }
}
