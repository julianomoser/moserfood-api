package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.ProdutoDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class ProdutosDTOOpenApi {

    private ProdutosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public class ProdutosEmbeddedModelOpenApi {

        private List<ProdutoDTO> produtos;

    }
}
