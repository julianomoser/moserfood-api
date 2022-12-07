package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.EstadoDTO;
import com.moser.moserfood.api.v1.model.input.EstadoInput;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

/**
 * @author Juliano Moser
 */
@SecurityRequirement(name = "security_oauth")
public interface EstadoControllerOpenApi {

    CollectionModel<EstadoDTO> listar();

    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    EstadoDTO buscar(Long estadoId);

    @ApiResponses(@ApiResponse(responseCode = "201", description = "Estado cadastrado"))
    EstadoDTO salvar(EstadoInput estadoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado atualizado"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    EstadoDTO atualizar(Long estadoId, EstadoInput estadoInput);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado excluído"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void delete(Long estadoId);

}
