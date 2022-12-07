package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.FormaPagamentoDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CollectionModel<FormaPagamentoDTO> listar(Long restauranteId);


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> associar(Long restauranteId,
                                  Long formaPagamentoId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Desssociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);


}
