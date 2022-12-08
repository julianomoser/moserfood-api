package com.moser.moserfood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Juliano Moser
 */
@Relation("fotos")
@Getter
@Setter
public class FotoProdutoDTO extends RepresentationModel<FotoProdutoDTO> {

    @Schema(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
    private String nomeArquivo;

    @Schema(example = "Mini NotChicken")
    private String descricao;

    @Schema(example = "image/jpeg")
    private String contentType;

    @Schema(example = "202912")
    private Long tamanho;
}
