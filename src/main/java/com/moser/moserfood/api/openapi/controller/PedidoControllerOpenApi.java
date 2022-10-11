package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.PedidoDTO;
import com.moser.moserfood.api.model.PedidoResumoDTO;
import com.moser.moserfood.api.model.input.PedidoInput;
import com.moser.moserfood.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Juliano Moser
 */
@Api(tags = "Order")
public interface PedidoControllerOpenApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisa os pedidos")
    PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter filtro, Pageable pageable);

    @ApiOperation("Registra um pedido")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Pedido cadastrado"))
    PedidoDTO adicionar(@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true)
                                   PedidoInput pedidoInput);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Busca um pedido por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do pedido inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    PedidoDTO buscar(@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
                            required = true)
                            String codigoPedido);
}
