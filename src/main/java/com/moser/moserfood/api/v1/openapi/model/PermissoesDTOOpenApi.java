package com.moser.moserfood.api.v1.openapi.model;

import com.moser.moserfood.api.v1.model.PermissaoDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Data
public class PermissoesDTOOpenApi {

    private PermissoesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public class PermissoesEmbeddedModelOpenApi {

        private List<PermissaoDTO> permissoes;

    }
}
