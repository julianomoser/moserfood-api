package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.FormaPagamentoDTO;
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
@Api(tags = "Restaurant")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @ApiOperation("Lista as formas de pagamento associadas a resturante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    List<FormaPagamentoDTO> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                                Long restauranteId);


    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void associar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                  Long restauranteId,
                  @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
                  Long formaPagamentoId);

    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Desssociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void desassociar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                     Long restauranteId,
                     @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
                     Long formaPagamentoId);


}
