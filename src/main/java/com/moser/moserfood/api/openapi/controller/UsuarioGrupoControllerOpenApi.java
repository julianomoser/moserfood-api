package com.moser.moserfood.api.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.GrupoDTO;
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
@Api(tags = "User")
public interface UsuarioGrupoControllerOpenApi {

    @ApiOperation(value = "Lista os grupos associados a um usuário")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
    @Schema(implementation = Problem.class)))
    List<GrupoDTO> listar(@ApiParam(value = "ID do usuário", example = "1", required = true)
                          Long usuarioId);

    @ApiOperation("Associação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void associar(@ApiParam(value = "ID do usuário", example = "1", required = true)
                  Long usuarioId,
                  @ApiParam(value = "ID do grupo", example = "1", required = true)
                  Long grupoId);

    @ApiOperation("Desssociação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Desssociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void desassociar(@ApiParam(value = "ID do usuário", example = "1", required = true)
                     Long usuarioId,
                     @ApiParam(value = "ID do grupo", example = "1", required = true)
                     Long grupoId);

}
