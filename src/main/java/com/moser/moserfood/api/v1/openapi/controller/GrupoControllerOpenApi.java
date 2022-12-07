package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.GrupoDTO;
import com.moser.moserfood.api.v1.model.input.GrupoInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
public interface GrupoControllerOpenApi {

    CollectionModel<GrupoDTO> listar();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    GrupoDTO buscar(Long grupoId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Grupo cadastrado"))
    GrupoDTO salvar(GrupoInput grupoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Grupo atualizado"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    GrupoDTO atualizar(Long grupoId, GrupoInput grupoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Grupo excluído"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long grupoId);

}
