package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.CozinhaDTO;
import com.moser.moserfood.api.v1.model.input.CozinhaInput;
import com.moser.moserfood.domain.model.Cozinha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.util.Optional;

/**
 * @author Juliano Moser
 */
@Api(tags = "Cuisine")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista as cozinhas")
    PagedModel<CozinhaDTO> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    CozinhaDTO buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    CozinhaDTO savlar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
                             CozinhaInput cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    CozinhaDTO atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                                Long cozinhaId,
                                @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados", required = true)
                                CozinhaInput cozinhaInput);

    @ApiOperation("Exclui uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Busca o primeiro restaurante")
    Optional<Cozinha> firstRestaurante();
}
