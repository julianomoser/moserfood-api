package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.v1.model.PedidoDTO;
import com.moser.moserfood.api.v1.model.PedidoResumoDTO;
import com.moser.moserfood.api.v1.model.input.PedidoInput;
import com.moser.moserfood.core.springdoc.PageableParameter;
import com.moser.moserfood.domain.filter.PedidoFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {

    @PageableParameter
    @Operation(summary = "Pesquisa os pedidos")
    PagedModel<PedidoResumoDTO> pesquisar(@Parameter(hidden = true) PedidoFilter filtro,
                                          @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Registra um pedido", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado")
    })
    PedidoDTO adicionar(@RequestBody(description = "Representação de um novo pedido", required = true)
                        PedidoInput pedidoInput);

    @Operation(summary = "Busca um pedido por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {@Content(schema =
            @Schema(ref = "Problema"))}),
    })
    PedidoDTO buscar(@Parameter(description = "Código de um pedido", example = "04813f77-79b5-11ec-9a17-0242ac1b0002",
            required = true) String codigoPedido);
}
