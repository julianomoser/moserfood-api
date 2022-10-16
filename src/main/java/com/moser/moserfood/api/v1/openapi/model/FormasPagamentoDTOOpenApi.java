package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.FormaPagamentoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@ApiModel("FormasPagamentoModel")
@Data
public class FormasPagamentoDTOOpenApi {

    private FormasPagamentoEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("FormasPagamentoEmbeddedModel")
    @Data
    public static class FormasPagamentoEmbeddedModelOpenApi {

        private List<FormaPagamentoDTO> formasPagamento;

    }
}
