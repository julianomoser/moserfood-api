package com.moser.moserfood.api.v2.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v2.model.CozinhaDTOV2;
import com.moser.moserfood.api.v2.model.input.CozinhaInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;

/**
 * @author Juliano Moser
 */
@Api(tags = "Cuisine", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CozinhaControllerV2OpenApi {

    @ApiOperation("Lista as cozinhas")
    PagedModel<CozinhaDTOV2> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CozinhaDTOV2 buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cozinha cadastrada"))
    CozinhaDTOV2 adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
                           CozinhaInputV2 CozinhaInputV2);

    @ApiOperation("Atualiza uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CozinhaDTOV2 atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
                           Long cozinhaId,
                           @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados", required = true)
                           CozinhaInputV2 CozinhaInputV2);

    @ApiOperation("Exclui uma cozinha por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);
}
