package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.CidadeDTO;
import com.moser.moserfood.api.model.input.CidadeInput;
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
@Api(tags = "City")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    public List<CidadeDTO> listar();

    @ApiOperation("Busca uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public CidadeDTO buscar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cidade cadastrada"))
    public CidadeDTO salvar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
                            CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public CidadeDTO atualizar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId,
                               @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
                               CidadeInput cidadeInput);

    @ApiOperation("Exclui uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    public void remover(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);
}