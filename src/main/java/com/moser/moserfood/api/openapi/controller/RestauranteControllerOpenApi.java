package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.RestauranteDTO;
import com.moser.moserfood.api.model.input.RestauranteInput;
import com.moser.moserfood.api.openapi.model.RestauranteBasicoDTOOpenApi;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Api(tags = "Restaurant")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoDTOOpenApi.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                    name = "projecao", paramType = "query", type = "string")
    })
    public List<RestauranteDTO> listar();

    @ApiOperation(value = "Lista restaurantes", hidden = true)
    public List<RestauranteDTO> listarApenasNomes();

    @ApiOperation("Busca um restaurante por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public RestauranteDTO buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Cadastra uma restaurante")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Restaurante cadastrada"))
    public RestauranteDTO salvar(@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true)
                                 RestauranteInput restauranteInput);

    @ApiOperation("Atualiza uma restaurante por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante atualizada"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public RestauranteDTO atualizar(@ApiParam(value = "ID de uma restaurante", example = "1", required = true) Long restauranteId,
                                    @ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", required = true)
                                    RestauranteInput restauranteInput);

    @ApiOperation("Exclui um restaurante por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do resrautante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void remover(@ApiParam(value = "ID de uma restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void ativar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);

    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void inativar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);

    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")
    })
    public void ativarMultiplos(
            @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
            List<Long> restauranteIds);

    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurantes inativados com sucesso")
    })
    public void inativarMultiplos(
            @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
            List<Long> restauranteIds);

    @ApiOperation("Abre um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void abrir(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);

    @ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void fechar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);
}
