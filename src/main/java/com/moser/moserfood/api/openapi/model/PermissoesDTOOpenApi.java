package com.moser.moserfood.api.openapi.model;

import com.moser.moserfood.api.model.PermissaoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@ApiModel("PermissoesModel")
@Data
public class PermissoesDTOOpenApi {

    private PermissoesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("PermissoesEmbeddedModel")
    @Data
    public class PermissoesEmbeddedModelOpenApi {

        private List<PermissaoDTO> permissoes;

    }
}
