package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.CozinhaDTO;
import com.moser.moserfood.api.model.input.CozinhaInput;
import com.moser.moserfood.domain.model.Cozinha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Juliano Moser
 */
@Api(tags = "Cuisine")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista as cozinhas")
    public Page<CozinhaDTO> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    public CozinhaDTO buscar(@ApiParam(value = "ID de uma cozinha", example = "1") Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    public CozinhaDTO savlar(CozinhaInput cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    public CozinhaDTO atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

    @ApiOperation("Exclui uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))  })
    public void remover( Long cozinhaId);

    @ApiOperation("Busca o primeiro restaurante")
    public Optional<Cozinha> firstRestaurante();
}
