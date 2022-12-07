package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.ProdutoDTO;
import com.moser.moserfood.api.v1.model.input.ProdutoInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
public interface RestauranteProdutoControllerOpenApi {

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CollectionModel<ProdutoDTO> listar(Long restauranteId, Boolean incluirInativos);


    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ProdutoDTO buscar(Long restauranteId, Long produtoId);


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto cadastrado"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ProdutoDTO salvar(Long restauranteId, ProdutoInput produtoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizada"),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ProdutoDTO atualizar(Long restauranteId, Long produtoId, ProdutoInput produtoInput);
}
