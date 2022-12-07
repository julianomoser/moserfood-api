package com.moser.moserfood.api.v2.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v2.model.CidadeDTOV2;
import com.moser.moserfood.api.v2.model.input.CidadeInputV2;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
public interface CidadeControllerV2OpenApi {

    CollectionModel<CidadeDTOV2> listar();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))
    })
    CidadeDTOV2 buscar(Long cidadeId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cidade cadastrada"))
    CidadeDTOV2 salvar(CidadeInputV2 cidadeInput);

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cidade cadastrada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))
    })
    CidadeDTOV2 atualizar(Long cidadeId, CidadeInputV2 cidadeInput);

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cidade cadastrada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))
    })
    void remover(Long cidadeId);
}
