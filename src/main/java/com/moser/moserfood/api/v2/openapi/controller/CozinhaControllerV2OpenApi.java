package com.moser.moserfood.api.v2.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v2.model.CozinhaDTOV2;
import com.moser.moserfood.api.v2.model.input.CozinhaInputV2;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

/**
 * @author Juliano Moser
 */
public interface CozinhaControllerV2OpenApi {

    PagedModel<CozinhaDTOV2> listar(Pageable pageable);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CozinhaDTOV2 buscar(Long cozinhaId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    CozinhaDTOV2 adicionar(CozinhaInputV2 CozinhaInputV2);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CozinhaDTOV2 atualizar(Long cozinhaId, CozinhaInputV2 CozinhaInputV2);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long cozinhaId);
}
