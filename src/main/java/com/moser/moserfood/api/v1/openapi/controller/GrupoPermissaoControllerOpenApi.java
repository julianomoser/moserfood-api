package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.PermissaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@Tag(name = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @Operation(summary = "Lista permissões")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CollectionModel<PermissaoDTO> listar(@Parameter(description = "ID de um grupo", example = "1", required = true)
                                         Long grupoId);

    @Operation(summary = "Associação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> associar(@Parameter(description = "ID de um grupo", example = "1", required = true)
                                  Long grupoId,
                                  @Parameter(description = "ID de uma permissão", example = "1", required = true)
                                  Long permissaoId);

    @Operation(summary = "Desassociação de permissão com grupo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Desssociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> desassociar(@Parameter(description = "ID de um grupo", example = "1", required = true)
                                     Long grupoId,
                                     @Parameter(description = "ID de uma permissão", example = "1", required = true)
                                     Long permissaoId);

}
