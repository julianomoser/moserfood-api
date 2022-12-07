package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.CidadeDTO;
import com.moser.moserfood.api.v1.model.input.CidadeInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_oauth")
public interface CidadeControllerOpenApi {

    CollectionModel<CidadeDTO> listar();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CidadeDTO buscar(Long cidadeId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cidade cadastrada"))
    CidadeDTO salvar(CidadeInput cidadeInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CidadeDTO atualizar(Long cidadeId, CidadeInput cidadeInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long cidadeId);
}
