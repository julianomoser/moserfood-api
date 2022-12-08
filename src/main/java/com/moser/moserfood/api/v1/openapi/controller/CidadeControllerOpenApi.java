package com.moser.moserfood.api.v1.openapi.controller;

import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.v1.model.CidadeDTO;
import com.moser.moserfood.api.v1.model.input.CidadeInput;
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
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

    @Operation(summary = "Lista as cidades")
    CollectionModel<CidadeDTO> listar();

    @Operation(summary = "Busca uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema =
            @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CidadeDTO buscar(Long cidadeId);

    @Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, necessita de um estado e um nome válido")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Cidade cadastrada"))
    CidadeDTO salvar(CidadeInput cidadeInput);

    @Operation(summary = "Atualiza uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    CidadeDTO atualizar(Long cidadeId, CidadeInput cidadeInput);

    @Operation(summary = "Exclui uma cidade por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema =
            @Schema(implementation = Problem.class)))})
    void remover(Long cidadeId);
}
