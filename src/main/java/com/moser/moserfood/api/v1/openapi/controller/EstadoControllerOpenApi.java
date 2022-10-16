package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.EstadoDTO;
import com.moser.moserfood.api.v1.model.input.EstadoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@Api(tags = "State")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista os estado")
    CollectionModel<EstadoDTO> listar();

    @ApiOperation("Busca um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    EstadoDTO buscar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

    @ApiOperation("Cadastra um estado")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Estado cadastrado"))
    EstadoDTO salvar(@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true)
                           EstadoInput estadoInput);

    @ApiOperation("Atualiza um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado atualizado"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    EstadoDTO atualizar(@ApiParam(value = "ID de um estado", example = "1", required = true)
                              Long estadoId,
                              @ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true)
                              EstadoInput estadoInput);

    @ApiOperation("Exclui um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado excluído"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void delete(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);

}
