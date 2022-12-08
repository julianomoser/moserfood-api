package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.EstadoDTO;
import com.moser.moserfood.api.v1.model.input.EstadoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_auth")
@Tag(name = "Estados")
public interface EstadoControllerOpenApi {

    @Operation(summary = "Lista os estado")
    CollectionModel<EstadoDTO> listar();

    @Operation(summary = "Busca um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    EstadoDTO buscar(Long estadoId);

    @Operation(summary = "Cadastra um estado")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Estado cadastrado"))
    EstadoDTO salvar(EstadoInput estadoInput);

    @Operation(summary = "Atualiza um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado atualizado"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    EstadoDTO atualizar(Long estadoId, EstadoInput estadoInput);

    @Operation(summary = "Exclui um estado por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado excluído"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void delete(Long estadoId);

}
