package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.FotoProdutoDTO;
import com.moser.moserfood.api.v1.model.input.FotoProdutoInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Juliano Moser
 */
public interface RestauranteProdutoFotoControllerOpenApi {

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Foto do produto atualizada"),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    FotoProdutoDTO atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput,
                                 MultipartFile arquivo) throws IOException;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema =
            @Schema(implementation = FotoProdutoDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "image/png")),
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "image/jpeg")),
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    FotoProdutoDTO buscar(Long restauranteId, Long produtoId);

//    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
//    ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
//            throws HttpMediaTypeNotAcceptableException;
//

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da foto inválida", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void delete(Long restauranteId, Long produtoId);

}
