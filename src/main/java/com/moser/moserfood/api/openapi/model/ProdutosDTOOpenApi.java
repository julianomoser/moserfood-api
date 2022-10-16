package com.moser.moserfood.api.openapi.model;

import com.moser.moserfood.api.model.ProdutoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@ApiModel("ProdutosModel")
@Data
public class ProdutosDTOOpenApi {

    private ProdutosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("ProdutosEmbeddedModel")
    @Data
    public class ProdutosEmbeddedModelOpenApi {

        private List<ProdutoDTO> produtos;

    }
}
