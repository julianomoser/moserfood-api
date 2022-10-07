package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.FotoProdutoDTO;
import com.moser.moserfood.api.model.input.FotoProdutoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

/**
 * @author Juliano Moser
 */
@Api(tags = "Restaurant")
public interface RestauranteProdutoFotoControllerOpenApi {

    @ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Foto do produto atualizada"),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    FotoProdutoDTO atualizarFoto(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                                 Long restauranteId,
                                 @ApiParam(value = "ID do produto", example = "1", required = true)
                                 Long produtoId,
                                 FotoProdutoInput fotoProdutoInput) throws IOException;

    @ApiOperation(value = "Busca a foto do produto de um restaurante",
            produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK", content = @Content(schema =
            @Schema(implementation = FotoProdutoDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "200",description = "OK", content = @Content(mediaType = "image/png")),
            @ApiResponse(responseCode = "200",description = "OK", content = @Content(mediaType = "image/jpeg")),
            @ApiResponse(responseCode = "400",description = "ID do restaurante ou produto inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404",description = "Foto de produto não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    FotoProdutoDTO buscar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                          Long restauranteId,
                          @ApiParam(value = "ID do produto", example = "1", required = true)
                          Long produtoId);

    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
    ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;


    @ApiOperation("Exclui uma foto por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da foto inválida", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void delete(@ApiParam(value = "ID de uma restaurante", example = "1", required = true)
                Long restauranteId,
                @ApiParam(value = "ID de uma produto", example = "1", required = true)
                Long produtoId);

}
