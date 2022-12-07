package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.PedidoDTO;
import com.moser.moserfood.api.v1.model.PedidoResumoDTO;
import com.moser.moserfood.api.v1.model.input.PedidoInput;
import com.moser.moserfood.domain.filter.PedidoFilter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
public interface PedidoControllerOpenApi {

    PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter filtro, Pageable pageable);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Pedido cadastrado"))
    PedidoDTO adicionar(PedidoInput pedidoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do pedido inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    PedidoDTO buscar(String codigoPedido);
}
