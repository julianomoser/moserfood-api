package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.CozinhaDTO;
import com.moser.moserfood.api.v1.model.input.CozinhaInput;
import com.moser.moserfood.domain.model.Cozinha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.Optional;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @Operation(summary = "Lista as cozinhas com paginação")
    PagedModel<CozinhaDTO> listar(Pageable pageable);

    @Operation(summary = "Busca uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema =
            @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(ref = "Problema")))
    })
    CozinhaDTO buscar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @Operation(summary = "Cadastra uma cozinha")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    CozinhaDTO savlar(@RequestBody(description = "Representação de uma nova cozinha", required = true)
                      CozinhaInput cozinhaInput);

    @Operation(summary = "Atualiza uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(ref = "Problema")))
    })
    CozinhaDTO atualizar(@Parameter(description = "ID de uma cozinha", example = "1", required = true)
                         Long cozinhaId,
                         @RequestBody(description = "Representação de uma nova cozinha", required = true)
                         CozinhaInput cozinhaInput);

    @Operation(summary = "Exclui uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(ref = "Problema")))
    })
    void remover(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @Operation(summary = "Busca o primeiro restaurante")
    Optional<Cozinha> firstRestaurante();
}
