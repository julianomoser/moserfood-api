package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.CozinhaDTO;
import com.moser.moserfood.api.v1.model.input.CozinhaInput;
import com.moser.moserfood.domain.model.Cozinha;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.Optional;

/**
 * @author Juliano Moser
 */
public interface CozinhaControllerOpenApi {

    PagedModel<CozinhaDTO> listar(Pageable pageable);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CozinhaDTO buscar(Long cozinhaId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    CozinhaDTO savlar(CozinhaInput cozinhaInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CozinhaDTO atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long cozinhaId);

    Optional<Cozinha> firstRestaurante();
}
