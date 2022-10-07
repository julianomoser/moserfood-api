package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.ProdutoDTO;
import com.moser.moserfood.api.model.RestauranteDTO;
import com.moser.moserfood.api.model.input.ProdutoInput;
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
@Api(tags = "Restaurant product")
public interface RestauranteProdutoControllerOpenApi {

    @ApiOperation(value = "Lista os produtos de um restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    List<ProdutoDTO> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                            Long restauranteId,
                            @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
                                    example = "false", defaultValue = "false")
                            boolean incluirInativos);


    @ApiOperation("Busca um produto por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ProdutoDTO buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true)
                          Long restauranteId,
                          @ApiParam(value = "ID de um produto", example = "1", required = true)
                          Long produtoId);


    @ApiOperation("Cadastra um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto cadastrado"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ProdutoDTO salvar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                      Long restauranteId,
                      @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true)
                      ProdutoInput produtoInput);

    @ApiOperation("Atualiza uma restaurante por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizada"),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ProdutoDTO atualizar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                         Long restauranteId,
                         @ApiParam(value = "ID do produto", example = "1", required = true)
                         Long produtoId,
                         @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados",
                                 required = true)
                         ProdutoInput produtoInput);
}
