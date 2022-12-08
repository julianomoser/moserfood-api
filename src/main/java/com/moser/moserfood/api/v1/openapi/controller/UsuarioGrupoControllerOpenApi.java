package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.GrupoDTO;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

    @Operation(summary = "Lista os grupos associados a um usuário")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema =
    @Schema(implementation = Problem.class)))
    CollectionModel<GrupoDTO> listar(Long usuarioId);

    @Operation(summary = "Associação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> associar(Long usuarioId, Long grupoId);

    @Operation(summary = "Desassociação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Desssociação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);

}
