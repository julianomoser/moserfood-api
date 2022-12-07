package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface FluxoPedidoControllerOpenApi {


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido confirnado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> confirmar(String codigoPedido);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Entrega do pedido registrada com suscesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> entregar(String codigoPedido);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> cancelar(String codigoPedido);
}
