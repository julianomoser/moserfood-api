package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.GrupoDTO;
import com.moser.moserfood.api.model.input.GrupoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Group")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    public List<GrupoDTO> listar();

    @ApiOperation("Busca um gurpo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public GrupoDTO buscar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Grupo cadastrado"))
    public GrupoDTO salvar(@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
                           GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Grupo atualizado"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public GrupoDTO atualizar(@ApiParam(value = "ID de um grupo", example = "1", required = true)
                              Long grupoId,
                              @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", required = true)
                              GrupoInput grupoInput);

    @ApiOperation("Exclui um grupo por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Grupo excluído"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void remover(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

}
