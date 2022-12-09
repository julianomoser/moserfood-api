package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.FotoProdutoDTO;
import com.moser.moserfood.api.v1.model.input.FotoProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

    @Operation(summary = "Atualiza a foto do produto de um restaurante")
    FotoProdutoDTO atualizarFoto(@Parameter(description = "Id do restaurante", example = "1", required = true)
                                 Long restauranteId,
                                 @Parameter(description = "Id do produto", example = "2", required = true)
                                 Long produtoId,
                                 @RequestBody(required = true)
                                 FotoProdutoInput fotoProdutoInput,
                                 MultipartFile arquivo) throws IOException;

    @Operation(summary = "Busca a foto do produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoDTO.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
            })
    })
    FotoProdutoDTO buscar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                          @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

    @Operation(hidden = true)
    ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;


    @Operation(summary = "Exclui uma foto por Id", responses = {
            @ApiResponse(responseCode = "204", description = "Foto do produto excluída"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {@Content(schema =
            @Schema(ref = "Problema"))}),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = {@Content(schema =
            @Schema(ref = "Problema"))}),
    })
    void delete(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
                @Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);

}
